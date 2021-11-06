package com.example.appbsgi

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import model.Usuario

class Cadastro : AppCompatActivity() {

    lateinit var editNome: EditText
    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editRepetirSenha: EditText
    lateinit var botaoCadastrar: Button
    lateinit var botaoAcessar: Button

    lateinit var bd: SQLiteDatabase // banco de dados local
    lateinit var values: ContentValues
    lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val novoUsuario = findViewById<EditText>(R.id.editRepetirSenha)
        novoUsuario.setOnClickListener(){
            insert()
        }

        linkarDados()
        criarAbrirBD()
    }

    fun linkarDados(){
        //variáveis Kotlin (acima) -> variáveis xml
        editNome  = findViewById(R.id.editNome)
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        editRepetirSenha = findViewById(R.id.editRepetirSenha)

        botaoCadastrar = findViewById(R.id.idBotaoCadastrar)
        botaoCadastrar.setOnClickListener(){
            insert()
            //update()
        }
        botaoAcessar = findViewById(R.id.idBotaoAcessar)
        botaoAcessar.setOnClickListener(){
            acessar()
        }

    }

    fun criarAbrirBD(){
        bd = openOrCreateDatabase("nomeBD.db", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS tbNova(nome varchar(60) NOT NULL, email varchar(60) NOT NULL, senha varchar(15) NOT NULL)")

    }

    fun insert(){
        usuario = Usuario(
            editNome.text.toString(),
            editEmail.text.toString(),
            editSenha.text.toString()
        )

        if (editRepetirSenha.text.equals(editSenha.text)) {
            Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Senhas não coincidem", Toast.LENGTH_LONG).show()
        }
    }

    fun acessar(){
        Toast.makeText(this,"Verificado com sucesso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, Principal::class.java)
        startActivity(intent)
    }






}