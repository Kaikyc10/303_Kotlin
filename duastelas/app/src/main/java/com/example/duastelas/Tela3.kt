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

        var btnVini = findViewById(R.id.btnBack1) as Button
        btnVini.setOnClickListener{
            this.finish()
        }


    }
}