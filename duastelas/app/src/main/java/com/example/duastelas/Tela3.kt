package com.example.duastelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Tela3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela3)

        val btnVoltar = findViewById(R.id.btnVoltar3) as Button
        btnVoltar.setOnClickListener{
            this.finish()
        }


        val btnVoltaTela1 = findViewById(R.id.btnBack1) as Button
        btnVoltaTela1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }


    }
}