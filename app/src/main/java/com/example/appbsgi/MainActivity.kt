package com.example.appbsgi

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import model.Usuario

class MainActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var botaoAcessar: Button


    lateinit var bd: SQLiteDatabase // banco de dados local
    lateinit var values: ContentValues
    lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val acessar = findViewById<Button>(R.id.idBotaoAcessar)
        acessar.setOnClickListener(){
            acessar()
        }

        val cadNew = findViewById<TextView>(R.id.idNovoCadastro)
        cadNew.setOnClickListener(){
            cadastroNovo()
        }

        val recupSenha = findViewById<TextView>(R.id.idRecuperarSenha)
        recupSenha.setOnClickListener(){
            mudarSenha()
        }

        linkarDados()
        criarAbrirBD()

    }


    fun linkarDados(){
        //variáveis Kotlin (acima) -> variáveis xml
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)

        botaoAcessar = findViewById(R.id.idBotaoAcessar)
        botaoAcessar.setOnClickListener(){
            acessar()
        }

    }

    fun criarAbrirBD(){
        bd = openOrCreateDatabase("nomeBD.db", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS tbNova(nome varchar(60) NOT NULL, email varchar(60) NOT NULL, senha varchar(15) NOT NULL)")

    }

    fun acessar(){
        Toast.makeText(this,"Verificado com sucesso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, Principal::class.java)
        startActivity(intent)
    }

    fun cadastroNovo(){
        val intent = Intent(this, Cadastro::class.java)
        startActivity(intent)
    }

    fun mudarSenha(){
        val intent = Intent(this, NovaSenha::class.java)
        startActivity(intent)
    }

}