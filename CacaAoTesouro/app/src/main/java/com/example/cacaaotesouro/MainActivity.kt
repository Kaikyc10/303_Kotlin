package com.example.cacaaotesouro


import ApiService
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.example.cacaaotesouro.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar a ApiService
        val apiService = RetrofitClient.retrofit.create(ApiService::class.java)
        val scanQrCodeButton = findViewById<Button>(R.id.scanQrCodeButton)
        scanQrCodeButton.setOnClickListener {
            startQrCodeScan()
        }

        // Verificar e solicitar permissão de câmera, se necessário
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }

        binding.sendDataButton.setOnClickListener {
            sendDataToSecondScreen()
        }
    }

    private fun startQrCodeScan() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false) // Permitir rotação da tela durante a leitura
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            val qrCodeMessage = result.contents
            binding.qrCodeMessageTextView.text = qrCodeMessage
        }
    }

    private fun sendDataToSecondScreen() {
        val qrCodeMessage = binding.qrCodeMessageTextView.text.toString()
        val ra = binding.raEditText.text.toString()

        // Verificar se todos os campos estão preenchidos
        if (qrCodeMessage.isEmpty() || ra.isEmpty()) {
            showToast("Por favor, preencha todos os campos.")
            return
        }

        // Enviar os dados para a segunda tela
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("qrCodeMessage", qrCodeMessage)
        intent.putExtra("ra", ra)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1001
    }
}
