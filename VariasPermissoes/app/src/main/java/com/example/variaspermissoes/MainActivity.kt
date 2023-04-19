package com.example.variaspermissoes

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.variaspermissoes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        var PERMISSIONS = arrayOf(
            CAMERA,
            RECORD_AUDIO
        )
    }


    private fun hasPermission(context: Context, permissions: Array<String>):
            Boolean = permissions.all{
                ActivityCompat.checkSelfPermission(context, it) ==
                        PackageManager.PERMISSION_GRANTED
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}