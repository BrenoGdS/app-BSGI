package com.example.appbsgi

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import model.Evento

class CadastroAtividade : AppCompatActivity() {

    lateinit var spinnerOrganizacao: Spinner
    lateinit var editTitulo: EditText
    lateinit var spinnerTipoEvento: Spinner
    lateinit var dataEvento: DatePicker
    lateinit var editCep: EditText
    lateinit var spinnerCidade: Spinner
    lateinit var editEndereco: EditText
    lateinit var editNumeroEndereco: EditText
    lateinit var editBairro: EditText
    lateinit var editComplemento: EditText
    lateinit var botaoSalvarAtividade: Button
    lateinit var botaoVoltar: Button
    lateinit var horaEvento: TimePicker


    lateinit var values: ContentValues
    lateinit var evento: Evento

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atividade)

        spinnerOrganizacao = findViewById(R.id.spinnerOrganizacao)
        editTitulo = findViewById(R.id.editTitulo)
        spinnerTipoEvento = findViewById(R.id.spinnerTipoEvento)
        dataEvento = findViewById(R.id.dataEvento)
        horaEvento = findViewById(R.id.horaEvento)
        horaEvento.setIs24HourView(true) // ajusta o TimePicker para 24 horas
        editCep = findViewById(R.id.editCep)
        spinnerCidade = findViewById(R.id.spinnerCidade)
        editEndereco = findViewById(R.id.editEndereco)
        editNumeroEndereco = findViewById(R.id.editNumeroEndereco)
        editComplemento = findViewById(R.id.editComplemento)
        editBairro = findViewById(R.id.editBairro)
        botaoSalvarAtividade = findViewById(R.id.botaoSalvarAtividade)
        botaoVoltar = findViewById(R.id.botaoVoltar)


        botaoSalvarAtividade.setOnClickListener(){
            insert()
        }
        botaoVoltar.setOnClickListener(){
            voltar()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun insert() {
        var cep:Int = 0
        if (!editCep.text.toString().equals("")) {
            cep=Integer.valueOf(editCep.text.toString())
        }
        var num:Int = 0
        if(!editNumeroEndereco.text.toString().equals("")){
            num=Integer.valueOf(editNumeroEndereco.text.toString())
        }
        var evento = Evento(
                0,
                0,
                editTitulo.text.toString(),
                dataEvento.year.toString() + "-" + (dataEvento.month + 1).toString() + "-" + dataEvento.dayOfMonth.toString() + " " + horaEvento.hour.toString() + ":" + horaEvento.minute.toString() + ":00",
                cep,
                0,
                editEndereco.text.toString(),
                num,
                editComplemento.text.toString(),
                editBairro.text.toString(),
                spinnerTipoEvento.selectedItem as String,
                spinnerOrganizacao.selectedItem as String,
                spinnerCidade.selectedItem as String
        )

        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/addEvento.php?" +
                "HTTP_TITULO=${evento.titulo}&" +
                "HTTP_BAIRROEVENTO=${evento.bairroevento}&" +
                "HTTP_CEPEVENTO=${evento.cepevento}&" +
                "HTTP_COMPLEMENTOEVENTO=${evento.complementoevento}&" +
                "HTTP_DATAEVENTO=${evento.dataevento}&" +
                "HTTP_DESCCIDADE=${evento.descCidade}&" +
                "HTTP_NOMEORG=${evento.nomeOrg}&" +
                "HTTP_DESCTIPOEVENTO=${evento.desctipoEvento}&" +
                "HTTP_LOGRADOUROEVENTO=${evento.logradouroevento}&" +
                "HTTP_NUMEVENTO=${evento.numevento}"


        val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { s ->
                    Toast.makeText(this, "Inserido com sucesso $url", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }
        )

        // Validação dos campos
        if (evento.titulo.equals("")) {
            editTitulo.requestFocus()
            Toast.makeText(this, "Favor preencher o título", Toast.LENGTH_LONG).show()
        }
        else if(evento.cepevento.equals(0)){
            editCep.requestFocus()
            Toast.makeText(this, "Favor preencher o CEP", Toast.LENGTH_LONG).show()
        }
        else if(evento.logradouroevento.equals("")){
            editEndereco.requestFocus()
            Toast.makeText(this, "Favor preencher o logradouro", Toast.LENGTH_LONG).show()
        }
        else if(evento.numevento.equals(0)){
            editNumeroEndereco.requestFocus()
            Toast.makeText(this, "Favor preencher o número do logradouro", Toast.LENGTH_LONG).show()
        }
        else if(evento.bairroevento.equals("")){
            editBairro.requestFocus()
            Toast.makeText(this, "Favor preencher o bairro", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_LONG).show()
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)
        }


    }


    fun voltar(){
        val intent = Intent(this, ConsultaEvento::class.java)
        startActivity(intent)
    }

}