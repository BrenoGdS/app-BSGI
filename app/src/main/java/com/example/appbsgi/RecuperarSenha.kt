package com.example.appbsgi

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import model.Usuario
import org.json.JSONArray
import org.json.JSONObject

class RecuperarSenha : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var botaoEnviar: Button

    lateinit var arrayUsuario: ArrayList<Usuario>
    lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)

        arrayUsuario = ArrayList<Usuario>()
        editEmail = findViewById(R.id.editEmail)

        //variáveis Kotlin (acima:lateinit) -> variáveis xml
        botaoEnviar = findViewById(R.id.idBotaoEnviar)
        botaoEnviar.setOnClickListener{
            verificarEmail()
        }
    }


    fun verificarEmail() {
        usuario = Usuario(editEmail.text.toString())
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/getUsuario.php?HTTP_EMAIL=${usuario.email}"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { s ->
                val jsonArray = JSONArray(s)
                var jsonObject = jsonArray.getJSONObject(0)
                val getEmailJson = jsonObject.getString("emailusuario")

                //if (getEmailJson.equals(editEmail)){
                if (getEmailJson.equals("null")){
                //if (jsonObject.getString("emailusuario").isNullOrEmpty()){
                    Toast.makeText(this,"Ops! E-mail não encontrado.",Toast.LENGTH_LONG).show()
                } else {
                    //Enviar email neste ponto
                    Toast.makeText(this,"Senha enviada. Verifique seu e-mail $getEmailJson",Toast.LENGTH_LONG).show()
                    //"Senha enviada. Verifique seu e-mail: ${getEmailJson.isNullOrBlank()}"
                    //limpar()
                }
            },
            Response.ErrorListener { error->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }
        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }



    fun limpar(){
        editEmail.setText(null)
        editEmail.requestFocus()
    }


    /*
    // verificar no BD se o email está cadastrado. Se não estiver, enviar erro. Se estiver, confirmar envio
    // NÃO USA PARA API:
    fun enviar(){
        // verificar no BD se o email está cadastrado. Se não estiver, enviar erro. Se estiver, confirmar envio
        //Toast.makeText(this,"E-mail não cadastrado", Toast.LENGTH_LONG).show()

        /* está pedindo celular, nome, etc
        usuario = Usuario(
            editEmail.text.toString()
        )

        values = ContentValues()
        values.put("email",usuario.email)
        */

        Toast.makeText(this,"Senha enviada. Verifique seu e-mail.", Toast.LENGTH_LONG).show()

        limpar()
    }
    */



}