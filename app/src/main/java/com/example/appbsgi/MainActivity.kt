package com.example.appbsgi

import android.content.Intent
//import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
//import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import model.Usuario
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var botaoAcessar: Button
    lateinit var botaoLogin:   Button

    lateinit var arrayUsuario: ArrayList<Usuario>
    lateinit var usuario: Usuario

    lateinit var nomeUser: String
    //lateinit var nomeUser: Usuario(usuario.nome)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arrayUsuario = ArrayList<Usuario>()
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)

        botaoAcessar = findViewById(R.id.idBotaoAcessar)
        botaoAcessar.setOnClickListener{
            acessar()
        }
        botaoLogin = findViewById(R.id.idBotaoLogin)
        botaoLogin.setOnClickListener{
            validarUsuario()
        }


        val cadNew = findViewById<TextView>(R.id.idNovoCadastro)
        cadNew.setOnClickListener(){
            cadastroNovo()
        }

        val recupSenha = findViewById<TextView>(R.id.idRecuperarSenha)
        recupSenha.setOnClickListener(){
            recuperarSenha()
        }


    }


    fun acessar(){

        if (!editEmail.text.toString().equals("")) {
            val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/getUsuario.php?HTTP_EMAIL=${usuario.email}"

                Toast.makeText(this, "Bem-vindo(a) ${usuario.email}", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Bem-vindo(a)", Toast.LENGTH_LONG).show()
            }

        val intent = Intent(this, ConsultaEvento::class.java)
        //val intent = Intent(this, Principal::class.java)  // opção anterior de teste
        startActivity(intent)

    }

    fun cadastroNovo(){
        val intent = Intent(this, Cadastro::class.java)
        startActivity(intent)
    }

    fun recuperarSenha(){
        val intent = Intent(this, RecuperarSenha::class.java)
        startActivity(intent)
    }

    fun validarUsuario(){
        usuario = Usuario(editEmail.text.toString(), editSenha.text.toString())
        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/getUsuario.php?HTTP_EMAIL=${usuario.email}&HTTP_SENHA=${usuario.senha}"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { s->
                val jsonArray = JSONArray(s)
                var jsonObject = jsonArray.getJSONObject(0)
                val emailJson = jsonObject.getString("emailusuario")
                val senhaJson = jsonObject.getString("senhausuario")
                val senhaDigit = editSenha.text.toString()

                //if (emailJson.equals("null") && senhaJson.equals(senhaDigit)) {
                //if (emailJson.equals("null") or senhaJson.equals ("null")) {
                if (emailJson.equals("null")){
                        Toast.makeText(this, "E-mail não cadastrado", Toast.LENGTH_LONG).show()
                    } else if (senhaDigit != (senhaJson)){
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_LONG).show()
                    editSenha.setText(null)
                    editSenha.requestFocus()

                } else {
                    //session()
                    acessar()
                    Toast.makeText(this,"Acesso validado",Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }
        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }



}