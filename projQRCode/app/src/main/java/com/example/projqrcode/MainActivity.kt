package com.example.projqrcode

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.google.zxing.qrcode.QRCodeWriter


class MainActivity : AppCompatActivity() {
    private lateinit var qrCodeImageView: ImageView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var qrCodeMessage: String? = null
    private var currentLocation: Location? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrCodeImageView = findViewById(R.id.qrCodeImageView)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val scanQrCodeButton: Button = findViewById(R.id.scanQrCodeButton)
        scanQrCodeButton.setOnClickListener {
            scanQrCode()
        }

        val takePhotoButton: Button = findViewById(R.id.takePhotoButton)
        takePhotoButton.setOnClickListener {
            takePhoto()
        }

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            submitData()
        }

        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            getCurrentLocation()
        }
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener(this, OnSuccessListener<Location> { location ->
                    if (location != null) {
                        currentLocation = location
                        // Aqui você pode usar a localização atual do usuário
                    }
                })
        } else {
            // Solicitar permissão de localização se ainda não foi concedida
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }


    private fun scanQrCode() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun submitData() {
        // Aqui você pode processar os dados, como enviar o RA e a localização do usuário
        if (qrCodeMessage != null && currentLocation != null) {
            // Exemplo de uso da mensagem do QR Code e localização do usuário
            val message = "QR Code: $qrCodeMessage\n" +
                    "Latitude: ${currentLocation?.latitude}\n" +
                    "Longitude: ${currentLocation?.longitude}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please scan a QR Code", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateQRCodeBitmap(rawBytes: ByteArray?): Bitmap? {
        if (rawBytes != null) {
            val matrix = QRCodeWriter().encode(String(rawBytes), BarcodeFormat.QR_CODE, 500, 500)
            val width = matrix.width
            val height = matrix.height
            val pixels = IntArray(width * height)

            for (y in 0 until height) {
                for (x in 0 until width) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = Color.BLACK
                    } else {
                        pixels[y * width + x] = Color.WHITE
                    }
                }
            }

            return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.RGB_565)
        }
        return null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            val result: IntentResult? =
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                } else {
                    qrCodeMessage = result.contents
                    val qrCodeBitmap = generateQRCodeBitmap(result.rawBytes)
                    qrCodeImageView.setImageBitmap(qrCodeBitmap)
                    Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}
