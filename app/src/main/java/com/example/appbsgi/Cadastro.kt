package com.example.appbsgi


import android.content.ContentValues
import android.content.Intent
//import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DownloadManager
import android.provider.SyncStateContract.Helpers.update
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import model.Usuario

class Cadastro : AppCompatActivity() {

    var id = Int
    lateinit var editNome: EditText
    lateinit var editCelular: EditText
    lateinit var sexo: Spinner
    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editRepetirSenha: EditText
    lateinit var botaoCadastrar: Button
    lateinit var botaoLimpar: Button
    //lateinit var botaoAcessar: Button

    //botão provisório para teste - colocar no lugar certo:
    lateinit var botaoEditarPerfil: Button

    lateinit var usuario: Usuario

    // NÃO USA PARA API:
    lateinit var values: ContentValues



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Associar variável do Kotlin com id do xml
        editNome  = findViewById(R.id.editNome)
        editCelular = findViewById(R.id.editCelular)
        sexo      = findViewById(R.id.spinnerSexo)
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        editRepetirSenha = findViewById(R.id.editRepetirSenha)

        botaoCadastrar = findViewById<Button>(R.id.idBotaoCadastrar)
        botaoCadastrar.setOnClickListener(){
            insert()
        }
        botaoLimpar = findViewById<Button>(R.id.idBotaoLimpar)
        botaoLimpar.setOnClickListener(){
            limpar()
        }


        // provisório p/ teste:
        /*
        botaoEditarPerfil = findViewById<Button>(R.id.idBotaoEditarPerfil)
        botaoEditarPerfil.setOnClickListener(){
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
            //update()
        }
        */



    }




    // INSERIR Usuário (API)
    // falta verificar se usuário já está cadastrado
    fun insert(){
        usuario = Usuario(
            editNome.text.toString(),
            editCelular.text.toString().toInt(),     // VERIFICAR AQUI
            sexo.selectedItem.toString(),
            editEmail.text.toString(),
            editSenha.text.toString()
        )


        //Funciona - achar o local do fluxo
        //if (!editNome.equals("null")) {
        //    Toast.makeText(this, "Favor preencher o nome", Toast.LENGTH_LONG).show()
        //}
        //else if (!editEmail.equals("null")){
        //    Toast.makeText(this, "Favor preencher  o e-mail", Toast.LENGTH_LONG).show()



        val url = "https://apimobileaularodrigo.000webhostapp.com/apiPI/addUsuario.php?HTTP_NOME=${usuario.nome}&HTTP_CELULAR=${usuario.celular}&HTTP_SEXO=${usuario.sexo}&HTTP_EMAIL=${usuario.email}&HTTP_SENHA=${usuario.senha}"

            //val stringRequest = StringRequest(
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { s->
                    Toast.makeText(this, "Inserido com sucesso $url", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }

        )

        // Verificação dos campos digitados:
        // Nome nulo ou <3
        if (editNome.text.toString().equals("") or (editNome.text.toString().length<3)) {
            editNome.requestFocus()
            Toast.makeText(this, "Favor preencher o nome", Toast.LENGTH_LONG).show()
        // Celular nulo ou <9
        } else if (editCelular.text.toString().equals("") or (editCelular.text.toString().length<9)) {
            editCelular.requestFocus()
            Toast.makeText(this, "Favor preencher o celular", Toast.LENGTH_LONG).show()
        }else if (editEmail.text.toString().equals("")){
            editEmail.requestFocus()
            Toast.makeText(this, "Favor preencher o e-mail", Toast.LENGTH_LONG).show()
        } else if (!editRepetirSenha.text.toString().equals(editSenha.text.toString())) {
            Toast.makeText(this,"Senhas não coincidem", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_LONG).show()

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)     // seria isso?
            limpar()
            //entrar na pagina principal:
            //acessar()
        }

    } // fim do insert



    fun limpar(){
        editNome.setText(null)
        editCelular.setText(null)
        editEmail.setText(null)
        editSenha.setText(null)
        editRepetirSenha.setText(null)
        editNome.requestFocus()
    }


    fun editarPerfil(){
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }







    /*
    // posso deletar
    fun acessar(){
        Toast.makeText(this,"Verificado com sucesso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, Principal::class.java)
        startActivity(intent)
    }
    */






    /*
    // NÃO USA PARA API:
    fun linkarDados(){
        //variáveis Kotlin (acima) -> variáveis xml
        editNome  = findViewById(R.id.editNome)
        editCelular = findViewById(R.id.editCelular)
        sexo = findViewById(R.id.spinnerSexo)

        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        editRepetirSenha = findViewById(R.id.editRepetirSenha)

        botaoCadastrar = findViewById(R.id.idBotaoCadastrar)
        botaoCadastrar.setOnClickListener(){
            insert()
            //update()
        }
        botaoEditarPerfil = findViewById(R.id.idBotaoEditarPerfil)
        botaoEditarPerfil.setOnClickListener(){
            editarPerfil()
        }
    }
    */


}