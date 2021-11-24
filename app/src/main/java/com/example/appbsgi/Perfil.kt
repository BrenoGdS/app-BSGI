package com.example.appbsgi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import model.Usuario
import org.json.JSONArray

class Perfil : AppCompatActivity() {

    lateinit var listViewUsuario: ListView
    lateinit var arrayUsuario: ArrayList<Usuario>
    lateinit var usuario: Usuario

    lateinit var editNome: EditText
    lateinit var editCelular: EditText
    lateinit var sexo: Spinner
    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var botaoAlterar: Button

    //var id = Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        arrayUsuario = ArrayList<Usuario>()

        // Associar variáveis do Kotlin (acima:lateinit) com id do xml
        editNome = findViewById(R.id.editNome)
        editCelular = findViewById(R.id.editCelular)
        sexo = findViewById(R.id.spinnerSexo)
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)

        botaoAlterar = findViewById(R.id.idBotaoAlterar)
        botaoAlterar.setOnClickListener(){
            //update()
        }

        listViewUsuario = findViewById(R.id.listViewUsuario)
        listViewUsuario.setOnItemClickListener { parent, view, position, id ->
            usuario = Usuario(
                arrayUsuario[position].id,
                arrayUsuario[position].nome,
                arrayUsuario[position].celular,
                arrayUsuario[position].sexo,
                arrayUsuario[position].email,
                arrayUsuario[position].senha
            )
        }



        carregarUsuario()
    }


    fun carregarUsuario(){
        //val id=1
        //val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/getUsuario.php?HTTP_ID=${usuario.id}"

        //val email = editEmail.text.toString()
        //val email = "rod@xmail.com"
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/getUsuario.php?HTTP_EMAIL=${usuario.email}"
        // tem que ter uma sessão associada ao email ou id para resgatar os dados?

        //carregando o url no array de usuários (requisição)
        //4 parâmetros
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { s->
                val jsonArray = JSONArray(s)
                var jsonObject = jsonArray.getJSONObject(0)

                usuario = Usuario(
                    //jsonObject.getInt("idusuario"),
                    jsonObject.getString("nomeusuario"),
                    jsonObject.getString("telefoneusuario"),
                    jsonObject.getString("sexo"),
                    jsonObject.getString("emailusuario"),
                    jsonObject.getString("senhausuario")
                )
                arrayUsuario.add(usuario)

                // Como preencher os campos com os dados obtidos do Json/banco?
                // Apresentar o conteúdo do array no listView
                    val adapterView = ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayUsuario)
                    listViewUsuario.adapter = adapterView


                Toast.makeText(this,"Exibindo dados do usuário",Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)     // seria isso?
    }







    fun update(){
        val id=0
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/updateUsuario.php?HTTP_ID=${usuario.id}"

        // tem que ter uma sessão associada ao email ou id para resgatar os dados
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { s->
                val jsonArray = JSONArray(s)
                var jsonObject = jsonArray.getJSONObject(0)
                val getIdJson = jsonObject.getString("idusuario")
                val getEmailJson = jsonObject.getString("emailusuario")

                // Como preencher os campos com os dados obtidos do Json/banco?

                Toast.makeText(this,"msg",Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)     // seria isso?




    }











}