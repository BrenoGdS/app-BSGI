package com.example.appbsgi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import model.Evento


class EditarEvento : AppCompatActivity() {
/*

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

    lateinit var evento: Evento



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_evento)

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
            editarEvento()
        }
        botaoVoltar.setOnClickListener(){
            //voltar()
        }




    }



    // Função para fazer update ao clicar em um item da lista
    // Chamar uma tela igual ao de CadastroAtividade

    @RequiresApi(Build.VERSION_CODES.M)
    fun editarEvento(){

        // VERIFICAR OS CAMPOS DA URL
        val url = "https://apiaulamobilerodrigo.000webhostapp.com/apiPI/updateEVENTO.php?HTTP_TITULO=${evento.titulo}&HTTP_PAIS=${evento.cepevento}&HTTP_TECNICO=${time.tecnico}&HTTP_ID=${time.id}"
        //val url = ""
        //carregando o url no array de times (requisição)
        //4 parâmetros
        val stringRequest = com.android.volley.toolbox.StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { s ->
                Toast.makeText(this, "Atualizado com sucesso $url", Toast.LENGTH_LONG).show()
                //carregarEventosAPI()
                //limpar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
        //carregarEventosAPI()

    }
*/


}