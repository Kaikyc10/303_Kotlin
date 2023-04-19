package com.example.listabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<*>
        val usuarios = arrayOf("Sao Paulo", "Minas Gerais", "Rio de Janeiro", "Parana",
            "Espirito Santo", "Bahia")

        //acessa a lista a partir de um arquivo xml
        val mListView = findViewById<ListView>(R.id.userList)
        // cria o adapter
        arrayAdapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, usuarios)
        mListView.adapter = arrayAdapter





    }
}