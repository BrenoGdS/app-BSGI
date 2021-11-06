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

class NovaSenha : AppCompatActivity() {

    lateinit var editEmail: EditText
    //lateinit var editSenha: EditText
    lateinit var editNovaSenha: EditText
    lateinit var editRepetirSenha: EditText
    lateinit var botaoAlterar: Button
    lateinit var botaoAcessar: Button

    lateinit var bd: SQLiteDatabase // banco de dados local
    lateinit var values: ContentValues
    lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_senha)

        val novaSenha = findViewById<EditText>(R.id.editRepetirSenha)
        //val novaSenha = findViewById<EditText>(R.id.editNovaSenha)
        novaSenha.setOnClickListener(){
            update()
        }

        linkarDados()
        criarAbrirBD()

    }

    fun linkarDados(){
        //variáveis Kotlin (acima) -> variáveis xml
        editEmail = findViewById(R.id.editEmail)
        //editSenha = findViewById(R.id.editSenha)
        editNovaSenha = findViewById(R.id.editNovaSenha)
        editRepetirSenha = findViewById(R.id.editRepetirSenha)

        botaoAlterar = findViewById(R.id.idBotaoAlterar)
        botaoAlterar.setOnClickListener(){
            //insert()
            update()
        }
        botaoAcessar = findViewById(R.id.idBotaoAcessar)
        botaoAcessar.setOnClickListener(){
            acessar()
        }
    }

    fun criarAbrirBD(){
        bd = openOrCreateDatabase("nomeBD.db", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS tbNova(email varchar(60) NOT NULL, senha varchar(15) NOT NULL)")

    }

    fun update(){

        /*
        if novasenha = repetirsenha then
        novasenha = senha
         */
        usuario = Usuario(
            editEmail.text.toString(),
            editNovaSenha.text.toString(),
            editRepetirSenha.text.toString(),
            //editSenha.text.toString()
            )
        values = ContentValues()
        values.put("senha",usuario.senha)

        if (editRepetirSenha.text.equals(editNovaSenha.text)) {
            //bd.update("tabelaBD", values, "email=?", arrayOf(usuario.email))

            Toast.makeText(this, "Senha alterada com sucesso", Toast.LENGTH_LONG).show()
            limpar()
        }else{
            Toast.makeText(this,"Senhas não coincidem", Toast.LENGTH_LONG).show()
        }
    }

    fun limpar(){
        editEmail.setText(null)
        editNovaSenha.setText(null)
        editRepetirSenha.setText(null)
        editEmail.requestFocus()

    }

    fun acessar(){
        Toast.makeText(this,"Verificado com sucesso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, Principal::class.java)
        startActivity(intent)
    }


}