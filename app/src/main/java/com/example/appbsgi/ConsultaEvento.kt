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
import java.util.ArrayList
import java.net.URL
import org.json.JSONObject
import org.json.JSONException

import android.widget.ArrayAdapter
import androidx.core.view.get


class ConsultaEvento : AppCompatActivity() {
    lateinit var listViewEventos: ListView
    lateinit var arrayEventos: ArrayList<Evento> //variável usada para guardar o conteúdo da URL(Json) da API

    lateinit var editTituloEvento: EditText
    lateinit var editOrganizacao: EditText
    lateinit var spinnerTipoEvento: Spinner
    lateinit var spinnerCidade: Spinner
    lateinit var dataEvento: DatePicker
    lateinit var horaEvento: TimePicker

    //Novo
    lateinit var editCep: EditText
    lateinit var editEndereco: EditText
    lateinit var editNumeroEndereco: EditText
    lateinit var editComplemento: EditText
    lateinit var editBairro: EditText
    lateinit var tituloListEventos: TextView
    lateinit var tituloEditarEventos: Button
    lateinit var tituloConsultarEventos:Button


    lateinit var buttonPesquisar: Button
    lateinit var buttonAlterar: Button // oculto
    lateinit var buttonLimpar: Button  // oculto
    lateinit var buttonExcluir: Button // oculto
    lateinit var buttonIncluirEvento: Button

    lateinit var evento: Evento


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_evento)

        arrayEventos = ArrayList<Evento>()


        editTituloEvento = findViewById(R.id.editTituloEvento)
        editOrganizacao = findViewById(R.id.editOrganizacao)
        spinnerTipoEvento = findViewById(R.id.spinnerTipoEvento)
        spinnerCidade = findViewById(R.id.spinnerCidade)
        dataEvento = findViewById(R.id.dataEvento)
        horaEvento = findViewById(R.id.horaEvento)
        horaEvento.setIs24HourView(true) // ajusta o TimePicker para 24 horas
        //Novo
        //tipoEvento = findViewById(R.id.spinnerTipoEvento)
        spinnerTipoEvento = findViewById(R.id.spinnerTipoEvento)

        buttonPesquisar = findViewById(R.id.buttonPesquisar)
        buttonIncluirEvento = findViewById(R.id.buttonIncluirEvento)
        // //listViewEventos = findViewById(R.id.listViewEventos)

        //Novo
        editCep = findViewById(R.id.editCep)
        editEndereco = findViewById(R.id.editEndereco)
        editNumeroEndereco=findViewById(R.id.editNumeroEndereco)
        editComplemento = findViewById(R.id.editComplemento)
        editBairro   = findViewById(R.id.editBairro)
        tituloListEventos = findViewById(R.id.tituloListEventos)
        tituloEditarEventos=findViewById(R.id.tituloEditarEventos)
        tituloConsultarEventos=findViewById(R.id.tituloConsultarEventos)


        //Testando novo objeto:     // sem erros
        /*
        evento = Evento(
            editTituloEvento.text.toString(),
            editOrganizacao.text.toString(),
            spinnerTipoEvento.selectedItem as String,
            //editCep.Int.toString(),   // está passando mesmo assim (verif)
            editEndereco.text.toString(),
            editComplemento.text.toString(),
            editBairro.text.toString()
        )
    */


        /*
        // sem erros
        evento = Evento(
            editTituloEvento.text.toString(),
            editOrganizacao.text.toString(),
            spinnerTipoEvento.selectedItem as String,
            spinnerCidade.selectedItem as String,
            dataEvento.isSelected as String
        )*/


        /*
        arrayEventos[position].idevento,

        arrayEventos[position].dataevento,
        arrayEventos[position].cepevento,
        arrayEventos[position].idcidadeevento,
        arrayEventos[position].logradouroevento,
        arrayEventos[position].numevento,
        arrayEventos[position].complementoevento,
        arrayEventos[position].bairroevento,
        arrayEventos[position].desctipoEvento)
        */



        //listViewEventos.selectedItem

        buttonPesquisar.setOnClickListener() {
            carregarEventosAPI()
        }
        buttonIncluirEvento.setOnClickListener() {
            incluirEvento()
        }

        buttonAlterar =findViewById(R.id.buttonAlterar)
        buttonAlterar.setOnClickListener{
            editarEvento()
        }
        buttonLimpar =findViewById(R.id.buttonLimpar)
        buttonLimpar.setOnClickListener{
            limpar()
        }
        buttonExcluir=findViewById(R.id.buttonExcluir)
        buttonExcluir.setOnClickListener {
            apagar()
        }



        // TRECHO P/ TENTAR POPULAR OS SPINNERS
        /*
        */



        // FIM DO TRECHO DE TESTE




        listViewEventos = findViewById(R.id.listViewEventos)
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


            // Passando os dados para os campos respectivos:

            editCep.setText(evento.cepevento.toString())
            editTituloEvento.setText(evento.titulo)
            editOrganizacao.setText(evento.nomeOrg)
            editEndereco.setText(evento.logradouroevento)
            editNumeroEndereco.setText(evento.numevento.toString())
            editComplemento.setText(evento.complementoevento)
            editBairro.setText(evento.bairroevento)

            ativarDesativarLayoutEdicao(true);



            //carregarEventosAPI()

        } //fim listViewEventos
    } // fim onCreate


    fun carregarEventosAPI() {
        tituloListEventos.visibility = TextView.VISIBLE
        listViewEventos.visibility = ListView.VISIBLE
        var evento = Evento(
            editTituloEvento.text.toString(),
            editOrganizacao.text.toString(),
            spinnerTipoEvento.selectedItem as String,
            spinnerCidade.selectedItem as String,
            dataEvento.year.toString() + '-' + (dataEvento.month + 1).toString() + '-' + dataEvento.dayOfMonth.toString()
        )
        if(evento.descCidade == "Selecione"){
            evento.descCidade = "";
        }
        if(evento.desctipoEvento == "Selecione"){
            evento.desctipoEvento = "";
        }
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
                    arrayEventos.add(evento)
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
        //tituloListEventos.requestFocus()
        //listViewEventos.setSelection(0)
        listViewEventos.setSelection(1)
    }

    fun incluirEvento() {
        val intent = Intent(this, CadastroAtividade::class.java)
        startActivity(intent)
    }

    fun limpar(){
        editTituloEvento.setText(null)
        editOrganizacao.setText(null)
        editCep.setText(null)
        editEndereco.setText(null)
        editNumeroEndereco.setText(null)
        editComplemento.setText(null)
        editBairro.setText(null)
        editTituloEvento.requestFocus()
    }

    fun ativarDesativarLayoutEdicao(isModoEdicao: Boolean){
        if(isModoEdicao){
            buttonPesquisar.visibility = Button.GONE
            buttonAlterar.visibility = Button.VISIBLE
            buttonLimpar.visibility  = Button.VISIBLE
            buttonExcluir.visibility = Button.VISIBLE
            buttonIncluirEvento.visibility = Button.GONE

            tituloEditarEventos.visibility=Button.VISIBLE
            tituloConsultarEventos.visibility=Button.GONE
            editCep.visibility = EditText.VISIBLE
            editEndereco.visibility = EditText.VISIBLE
            editNumeroEndereco.visibility=EditText.VISIBLE
            editComplemento.visibility = EditText.VISIBLE
            editBairro.visibility = EditText.VISIBLE
            //Novo
            horaEvento.visibility = TimePicker.VISIBLE
            tituloListEventos.visibility = TextView.GONE
            listViewEventos.visibility = ListView.GONE

        }else{

            buttonPesquisar.visibility = Button.VISIBLE
            buttonAlterar.visibility = Button.GONE
            buttonLimpar.visibility  = Button.GONE
            buttonExcluir.visibility = Button.GONE
            buttonIncluirEvento.visibility = Button.VISIBLE

            tituloEditarEventos.visibility=Button.GONE
            tituloConsultarEventos.visibility=Button.VISIBLE
            editCep.visibility = EditText.GONE
            editEndereco.visibility = EditText.GONE
            editNumeroEndereco.visibility=EditText.GONE
            editComplemento.visibility = EditText.GONE
            editBairro.visibility = EditText.GONE
            //Novo
            horaEvento.visibility = TimePicker.GONE
            tituloListEventos.visibility = TextView.VISIBLE
            //listViewEventos.visibility = ListView.VISIBLE
        }
    }


    fun apagar(){
        // Precisa dessas linhas para usar o construtor delete()
        //val idevento=0
        //evento = Evento(idevento)

        // Funcionando
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/deleteEvento.php?HTTP_ID=${evento.idevento}"
        //carregando o url no array de eventos (requisição)
        //4 parâmetros
        val stringRequest = StringRequest1(
            Request.Method.GET,
            url,
            Response.Listener { s ->
                Toast.makeText(this, "Deletado com sucesso $url", Toast.LENGTH_LONG).show()
                //carregarEventosAPI()
                limpar()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
        arrayEventos.clear()
        listViewEventos.setAdapter(null);
        ativarDesativarLayoutEdicao(false)
        carregarEventosAPI()
    }




    // Função para fazer update ao clicar em um item da lista

    //verificar:
    //"&HTTP_HORA=${evento.hora}" +
    // adicionar novamente:
    /*
    "&HTTP_NOMEORG=${evento.nomeOrg} + " +
    "&HTTP_DESCTIPOEVENTO=${evento.desctipoEvento}" +
    "&HTTP_DATA=${evento.dataevento}" +
    "&HTTP_DESCCIDADE=${evento.descCidade}" +
    */
    fun editarEvento(){
        // teste 0512
        evento = Evento(
            editTituloEvento.text.toString(),
            editCep.text.toString(),
            //editCep.Int.toString(),   // está passando mesmo assim (verif)
            //editOrganizacao.text.toString(),
            //spinnerTipoEvento.selectedItem as String,
            editEndereco.text.toString(),
            editNumeroEndereco.text.toString(),
            editComplemento.text.toString(),
            editBairro.text.toString()
        )

        // VERIFICAR OS CAMPOS DA URL
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/updateEvento.php?" +
                "HTTP_TITULO=${evento.titulo}" +
                "&HTTP_CEPEVENTO=${evento.cepevento}" +
                "&HTTP_LOGRADOUROEVENTO=${evento.logradouroevento}" +
                "&HTTP_NUMEVENTO=${evento.numevento}" +
                "&HTTP_COMPLEMENTOEVENTO=${evento.complementoevento}" +
                "&HTTP_BAIRROEVENTO=${evento.bairroevento}" +
                "&HTTP_ID=${evento.idevento}"

        //carregando o url no array de eventos (requisição)
        //4 parâmetros
        //val stringRequest = com.android.volley.toolbox.StringRequest(
        val stringRequest = StringRequest1(
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

        arrayEventos.clear()
        listViewEventos.setAdapter(null)
        ativarDesativarLayoutEdicao(false)
        carregarEventosAPI()
    }



}