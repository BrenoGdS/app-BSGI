package com.example.appbsgi

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import model.Usuario

class RecuperarSenha : AppCompatActivity() {

    lateinit var editEmail: EditText

    lateinit var botaoEnviar: Button


    lateinit var bd: SQLiteDatabase // banco de dados local
    lateinit var values: ContentValues
    lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)

        linkarDados()
    }

    fun linkarDados() {
        //variáveis Kotlin (acima) -> variáveis xml
        editEmail = findViewById(R.id.editEmail)

        botaoEnviar = findViewById(R.id.idBotaoEnviar)
        botaoEnviar.setOnClickListener() {
            enviar()
        }
    }


    /*
        //Não precisa disso
    fun criarAbrirBD(){
        bd = openOrCreateDatabase("nomeBD.db", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS tbNova(email varchar(60) NOT NULL, senha varchar(15) NOT NULL)")
    }
        */



    // verificar no BD se o email está cadastrado. Se não estiver, enviar erro. Se estiver, confirmar envio

    fun enviar(){
        // verificar no BD se o email está cadastrado. Se não estiver, enviar erro. Se estiver, confirmar envio
        //Toast.makeText(this,"E-mail não cadastrado", Toast.LENGTH_LONG).show()

        /* está pedindo celular, nome, etc
        usuario = Usuario(
            editEmail.text.toString()
        )

        values = ContentValues()
        values.put("email",usuario.email)
        */


        Toast.makeText(this,"Senha enviada. Verifique seu e-mail.", Toast.LENGTH_LONG).show()

        limpar()
    }

    fun limpar(){
        editEmail.setText(null)
        editEmail.requestFocus()
    }



}