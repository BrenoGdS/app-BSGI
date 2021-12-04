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
import android.content.Intent





class EditarEvento : AppCompatActivity() {
// /*

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

    lateinit var listViewEventos: ListView
    lateinit var arrayEventos: ArrayList<Evento>

    lateinit var evento: Evento

    // recebendo valor da activity anterior
    lateinit var titulo:String


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_evento)

        arrayEventos = ArrayList<Evento>()

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

        listViewEventos = findViewById(R.id.listViewEventos)


        /*
        // solução do Breno:
        val it:Intent = this.intent
        //Recuperei a string da outra activity
        //val informacao = it.getStringExtra("titulo")
        */

        // Passando os dados para os campos respectivos:
            //editTitulo.setText(it.getStringExtra("titulo"))
        editTitulo.setText(evento.titulo)
        editCep.setText(evento.cepevento)
        editEndereco.setText(evento.logradouroevento)


        listViewEventos.setOnItemClickListener { parent, view, position, id ->
        evento = Evento(
            arrayEventos[position].idevento,
            arrayEventos[position].idtipoevento,
            arrayEventos[position].titulo,
            arrayEventos[position].dataevento,
            arrayEventos[position].cepevento,
            arrayEventos[position].idcidadeevento,
            arrayEventos[position].logradouroevento,
            arrayEventos[position].numevento,
            arrayEventos[position].complementoevento,
            arrayEventos[position].bairroevento,
            arrayEventos[position].desctipoEvento,
            arrayEventos[position].nomeOrg,
            arrayEventos[position].descCidade)

            Toast.makeText(this,"Evento: "+evento,Toast.LENGTH_LONG).show()



        } // fim do listView
        //carregarEventosAPI()



    } // fim do onCreate



    // Função para fazer update ao clicar em um item da lista
    // Chamar uma tela igual ao de CadastroAtividade

    @RequiresApi(Build.VERSION_CODES.M)
    fun editarEvento(){

        // VERIFICAR OS CAMPOS DA URL
        val url = "https://apiaulamobilerodrigo.000webhostapp.com/apiPI/updateEVENTO.php?HTTP_TITULO=${evento.titulo}&HTTP_CEPEVENTO=${evento.cepevento}&HTTP_LOGRADOUROEVENTO=${evento.logradouroevento}&HTTP_NUMEVENTO=${evento.numevento}&HTTP_COMPLEMENTOEVENTO=${evento.complementoevento}&HTTP_BAIRROEVENTO=${evento.bairroevento}"
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
//*/


}