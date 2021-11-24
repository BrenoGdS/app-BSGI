package com.example.appbsgi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Principal : AppCompatActivity() {

    lateinit var botaoPerfil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)


        botaoPerfil = findViewById(R.id.idBotaoEditarPerfil)
        botaoPerfil.setOnClickListener(){
            perfil()
        }
    }

    fun perfil(){
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }




}