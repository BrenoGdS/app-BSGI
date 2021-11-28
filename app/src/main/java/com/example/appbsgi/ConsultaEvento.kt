package com.example.appbsgi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import model.Evento
import org.json.JSONArray
import com.android.volley.toolbox.StringRequest as StringRequest1

class ConsultaEvento : AppCompatActivity() {
    lateinit var editTituloEvento: EditText
    lateinit var editOrganizacao: EditText
    lateinit var spinnerTipoEvento: Spinner
    lateinit var spinnerCidade: Spinner
    lateinit var dateNiver: DatePicker
    lateinit var buttonPesquisar: Button
    lateinit var listViewEventos: ListView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_evento)
        arrayEventos = ArrayList<Evento>()

        listViewEventos = findViewById(R.id.listViewEventos)
        listViewEventos.setOnItemClickListener{ parent, view, position, id ->
            evento = Evento()
            editTituloEvento = findViewById(R.id.editTituloEvento)


                arrayTimes[position].idevento,
                arrayTimes[position].organizacao,
                arrayTimes[position].idtipoevento,
                arrayTimes[position].titulo,
                arrayTimes[position].dataevento,
                arrayTimes[position].cepevento,
                arrayTimes[position].idcidadeevento),
                arrayTimes[position].logradouroevento,
                arrayTimes[position].numevento,
                arrayTimes[position].complementoevento,
                arrayTimes[position].bairroevento)


            )
    }

    fun carregarEventosAPI() {
        val url = "https://apiaulamauricio.000webhostapp.com/apiPI/getAllEventos.php"
        val stringRequest = StringRequest1(
            Request.Method.GET,
            url,
            Response.Listener { s ->
                val jsonArray = JSONArray(s)
                for (i in 0..jsonArray.length() - 1) {
                    var jsonObject = jsonArray.getJSONObject(i)

                    evento = Evento(
                        jsonObject.getInt("idevento"),
                        jsonObject.getString("organizacao"),
                        jsonObject.getString("titulo"),
                        jsonObject.getString("dataevento"),
                        jsonObject.getString("cepevento"),
                        jsonObject.getString("cidadeevento"),
                        jsonObject.getString("dataevento"),
                        jsonObject.getString("logradouroevento "),
                        jsonObject.getString("numevento "),
                        jsonObject.getString("complementoevento"),
                        jsonObject.getString("bairroevento  "),

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
}
