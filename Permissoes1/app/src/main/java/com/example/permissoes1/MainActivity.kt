package com.example.permissoes1

import android.content.pm.PackageManager
import android.Manifest.permission.CAMERA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.permissoes1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val permissionActResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
        var msg: String = ""
        when(it){
            true -> { msg = "Permissao aceita"}
            false -> { msg = "Permissao negada"}
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text ="salve"

        binding.btnPermi.setOnClickListener {
            binding.textView.text = "ain"
            if(checkPermission()){
            permissionActResult.launch(CAMERA)
        }
        else {
                println("Pode chamar algo...")
            }


    }

}
    /*cria uma função para verificar se a permissão está ativa ou não*/
    private fun checkPermission(): Boolean{
        val permission = ContextCompat.checkSelfPermission(
            this, CAMERA)

        return permission != PackageManager.PERMISSION_GRANTED
    }
}