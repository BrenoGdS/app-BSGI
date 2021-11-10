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
    lateinit var editCelular: EditText
    //spinnerSexo
    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editRepetirSenha: EditText
    lateinit var botaoCadastrar: Button
    //lateinit var botaoAcessar: Button
    //botão provisório para teste:
    lateinit var botaoEditarPerfil: Button


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
        editCelular = findViewById(R.id.editCelular)
        //spinnerSexo
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        editRepetirSenha = findViewById(R.id.editRepetirSenha)

        botaoCadastrar = findViewById(R.id.idBotaoCadastrar)
        botaoCadastrar.setOnClickListener(){
            insert()
            //update()
        }
        botaoEditarPerfil = findViewById(R.id.idBotaoEditarPerfil)
        botaoEditarPerfil.setOnClickListener(){
            editarPerfil()
        }


    }

    fun criarAbrirBD(){
        bd = openOrCreateDatabase("bdappbsgi.db", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS tbappbsgi(nome varchar(60) NOT NULL, celular bigint(11), email varchar(60) NOT NULL, senha varchar(15) NOT NULL)")

    }

    // falta spinnerSexo
    // verificar se usuário já está cadastrado
    fun insert(){
        usuario = Usuario(
            editNome.text.toString(),
            editCelular.text.toString(),
            editEmail.text.toString(),
            editSenha.text.toString()
        )

        if (editRepetirSenha.text.toString().equals(editSenha.text.toString())) {
            Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_LONG).show()
            limpar()
        }else{
            Toast.makeText(this,"Senhas não coincidem", Toast.LENGTH_LONG).show()
        }
    }


    // falta spinnerSexo
    fun update(){
        usuario = Usuario(
            editNome.text.toString(),
            editCelular.text.toString(),
            editEmail.text.toString(),
            editSenha.text.toString()
        )
        values = ContentValues()
        values.put("nome",usuario.nome)
        values.put("celular",usuario.celular)
        values.put("email",usuario.email)
        values.put("senha",usuario.senha)

        bd.update("tbappbsgi",values,"email", arrayOf(usuario.email))
        Toast.makeText(this,"Atualizado com sucesso",Toast.LENGTH_LONG).show()

    }


    fun editarPerfil(){
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }

    /*
    // posso deletar
    fun acessar(){
        Toast.makeText(this,"Verificado com sucesso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, Principal::class.java)
        startActivity(intent)
    }
    */


    fun limpar(){
        editNome.setText(null)
        editCelular.setText(null)
        editEmail.setText(null)
        editSenha.setText(null)
        editRepetirSenha.setText(null)
        editNome.requestFocus()
    }



}