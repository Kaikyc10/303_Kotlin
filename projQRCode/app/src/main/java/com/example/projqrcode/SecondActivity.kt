package com.example.projqrcode

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val qrCode = "QR Code lido" // Substitua pelo QR Code lido
        val fotoTirada = "Caminho da foto" // Substitua pelo caminho da foto tirada
        val ra = intent.getStringExtra("ra")
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        qrCodeImageView.setImageURI(Uri.parse(qrCode)) // Exibe o QR Code
        fotoImageView.setImageURI(Uri.parse(fotoTirada)) // Exibe a foto tirada
        raTextView.text = ra // Exibe o RA digitado
        localizacaoTextView.text = "Latitude: $latitude, Longitude: $longitude" // Exibe a localização
    }
}
