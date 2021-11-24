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
    lateinit var listViewEventos: ListView
    lateinit var arrayEventos: ArrayList<Evento>
    lateinit var evento: Evento
    lateinit var editNome: EditText
    lateinit var spinnerEstado: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_evento)
        listViewEventos = findViewById(R.id.listViewEventos)
    }

    fun carregarEventosAPI() {
        val url = "https://apiaulamobile.000webhostapp.com/apifutebol/getAllTimes.php"
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
                        jsonObject.getString("estado"),
                        jsonObject.getString("data"),
                        jsonObject.getString("titulo"),
                        jsonObject.getString("logradouroevento")
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
