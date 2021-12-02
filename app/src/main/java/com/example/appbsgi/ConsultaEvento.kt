package com.example.appbsgi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import model.Evento
import org.json.JSONArray
import java.time.LocalDateTime
import com.android.volley.toolbox.StringRequest as StringRequest1

class ConsultaEvento : AppCompatActivity() {
    lateinit var editTituloEvento: EditText
    lateinit var editOrganizacao: EditText
    lateinit var spinnerTipoEvento: Spinner
    lateinit var spinnerCidade: Spinner
    lateinit var dataEvento: DatePicker
    lateinit var buttonPesquisar: Button
    lateinit var buttonIncluirEvento: Button
    lateinit var listViewEventos: ListView
    lateinit var evento: Evento
    lateinit var arrayEventos: ArrayList<Evento>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_evento)
        arrayEventos = ArrayList<Evento>()

        editTituloEvento = findViewById(R.id.editTituloEvento)
        editOrganizacao = findViewById(R.id.editOrganizacao)
        spinnerTipoEvento = findViewById(R.id.spinnerTipoEvento)
        spinnerCidade = findViewById(R.id.spinnerCidade)
        dataEvento = findViewById(R.id.dataEvento)
        buttonPesquisar = findViewById(R.id.buttonPesquisar)
        buttonIncluirEvento = findViewById(R.id.buttonIncluirEvento)
        listViewEventos = findViewById(R.id.listViewEventos)

        buttonPesquisar.setOnClickListener() {
            carregarEventosAPI()
        }
        buttonIncluirEvento.setOnClickListener() {
            incluirEvento()
        }

        listViewEventos.setOnItemClickListener { parent, view, position, id ->
            var evento = Evento(
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
        }
    }


    fun carregarEventosAPI() {

        var evento = Evento(
                editTituloEvento.text.toString(),
                editOrganizacao.text.toString(),
                spinnerTipoEvento.selectedItem as String,
                spinnerCidade.selectedItem as String,
                dataEvento.year.toString() + '-' + (dataEvento.month + 1).toString() + '-' + dataEvento.dayOfMonth.toString()
        )
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/getEvento.php?" +
                "HTTP_TITULO=${evento.titulo}" +
                "&HTTP_NOMEORG=${evento.nomeOrg}" +
                "&HTTP_DESCTIPOEVENTO=${evento.desctipoEvento}" +
                "&HTTP_DESCCIDADE=${evento.descCidade}" +
                "&HTTP_DATA=${evento.dataevento}"

        val stringRequest = StringRequest1(
                Request.Method.GET,
                url,
                Response.Listener { s ->
                    val jsonArray = JSONArray(s)
                    for (i in 0..jsonArray.length() - 1) {
                        var jsonObject = jsonArray.getJSONObject(i)

                        var evento = Evento(jsonObject.getInt("idevento"),
                                jsonObject.getInt("idtipoevento"),
                                jsonObject.getString("titulo"),
                                jsonObject.getString("dataevento"),
                                jsonObject.getInt("cepevento"),
                                jsonObject.getInt("idcidadeevento"),
                                jsonObject.getString("logradouroevento"),
                                jsonObject.getInt("numevento"),
                                jsonObject.getString("complementoevento"),
                                jsonObject.getString("bairroevento"),
                                jsonObject.getString("desctipoevento"),
                                jsonObject.getString("nomeorg"),
                                jsonObject.getString("desccidade")
                        )
                        arrayEventos.add(evento);
                    }

                    val adapterView = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayEventos)
                    listViewEventos.adapter = adapterView
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }

        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    fun incluirEvento() {
        val intent = Intent(this, CadastroAtividade::class.java)
        startActivity(intent)
    }




    // Função para fazer update ao clicar em um item da lista
    // Chamar uma tela igual ao de CadastroAtividade
    /*
    fun editarEvento(){

        // VERIFICAR OS CAMPOS DA URL
        val url = "https://apiaulamobilerodrigo.000webhostapp.com/apiPI/updateEVENTO.php?HTTP_TIME=${time.time}&HTTP_PAIS=${time.pais}&HTTP_TECNICO=${time.tecnico}&HTTP_ID=${time.id}"
        //val url = ""
        //carregando o url no array de times (requisição)
        //4 parâmetros
        val stringRequest = com.android.volley.toolbox.StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { s ->
                    Toast.makeText(this, "Atualizado com sucesso $url", Toast.LENGTH_LONG).show()
                    carregarEventosAPI()
                    //limpar()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }
        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
        //carregarEventosAPI()

    }*/




}
