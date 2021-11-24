package com.example.appbsgi

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import model.Evento

class CadastroAtividade : AppCompatActivity() {

    lateinit var editTitulo: EditText
    lateinit var editEndereco: EditText
    lateinit var editNumeroEndereco: EditText
    lateinit var editComplemento: EditText
    lateinit var editCep: EditText
    lateinit var botaoSalvarAtividade: Button
    lateinit var botaoVoltar: Button


    lateinit var bd: SQLiteDatabase // banco de dados local
    lateinit var values: ContentValues
    lateinit var evento: Evento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        linkarDados()
    }

    fun linkarDados(){

        editTitulo = findViewById(R.id.editTitulo)
        editEndereco = findViewById(R.id.editEndereco)
        editNumeroEndereco = findViewById(R.id.editNumeroEndereco)
        editComplemento = findViewById(R.id.editComplemento)
        editCep = findViewById(R.id.editCep)

        botaoSalvarAtividade = findViewById(R.id.botaoSalvarAtividade)
        botaoSalvarAtividade.setOnClickListener(){
            insert()
            //update()
        }
        botaoVoltar = findViewById(R.id.botaoVoltar)
        botaoVoltar.setOnClickListener(){
            //voltar()
        }

    }

    fun insert() {/*
        evento = Evento(
            0, // idEvento
            1, //idOrganizacao:Int,
            1, //idTipoEvento:Int,
            editTitulo.text.toString(),
            "2021-11-01",//dataEvento:String,
            editCep.text.toString(),
            1, //idCidadeEvento:Int,
            editEndereco.text.toString(), //logradouroEvento:String,
            editNumeroEndereco.text.toString(), //numEvento:String,
            editComplemento.text.toString(), //complementoEvento:String,
            "bairro" //bairro
        )*/
    }

}