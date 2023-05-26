package com.example.cacaaotesouro

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.example.cacaaotesouro.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alunoResponse = intent.getParcelableExtra<AlunoResponse>("alunoResponse")
        if (alunoResponse != null) {
            binding.qrCodeTextView.text = alunoResponse.qrCode
            val decodedBitmap = decodeBase64ToBitmap(alunoResponse.photoBase64)
            binding.photoImageView.setImageBitmap(decodedBitmap)
            binding.raTextView.text = alunoResponse.ra
            binding.locationTextView.text = "Latitude: ${alunoResponse.latitude}, Longitude: ${alunoResponse.longitude}"
        }
    }

    private fun decodeBase64ToBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}




