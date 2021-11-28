package com.example.appbsgi

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    //lateinit var botaoAcessar: Button
    //botão provisório para teste:
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



        // NÃO USA PARA API:
        /*
        val botaoCadastrar = findViewById<Button>(R.id.idBotaoCadastrar)
        botaoCadastrar.setOnClickListener(){
            insert()
        }
        */


        // NÃO USA PARA API:
        //linkarDados()

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

        /*
        // Não está funcionando assim:
        if (editNome.text.toString().equals("")){
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_LONG).show()
        }
        */

        //Funciona - achar o local do fluxo
        //if (!editNome.equals("null")) {
        //    Toast.makeText(this, "Favor preencher o nome", Toast.LENGTH_LONG).show()
        //}
        //else if (!editEmail.equals("null")){
        //    Toast.makeText(this, "Favor preencher  o e-mail", Toast.LENGTH_LONG).show()


        // parte nova do API
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

            //val requestQueue = Volley.newRequestQueue(this)
            //requestQueue(stringRequest)  // PROBLEMA AQUI, RESOLVER
        // fim da parte nova do API


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




        /*
        //como o if das senhas estava originalmente
        if (editRepetirSenha.text.toString().equals(editSenha.text.toString())) {
            Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_LONG).show()
            //limpar()

            // retirar daqui
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)     // seria isso?
        }else{
            Toast.makeText(this,"Senhas não coincidem", Toast.LENGTH_LONG).show()
        }
        */

        /*
        if (!editNome.equals("null")) {
            Toast.makeText(this, "Favor preencher o nome", Toast.LENGTH_LONG).show()
        } else if (editRepetirSenha.text.toString().equals(editSenha.text.toString())) {
            Toast.makeText(this, "Registrado com sucesso", Toast.LENGTH_LONG).show()
            //limpar()

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)     // seria isso?
        }else{
            Toast.makeText(this,"Senhas não coincidem", Toast.LENGTH_LONG).show()
        }
        */



        // OUTRAS TENTATIVAS DE VERIFICAÇÃO:
        /*
        if (editNome == (null)) {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_LONG).show()
        }
        */

        // Não identifica o campo em branco:
        // if (!editNome.text.toString().equals("")) {

        // Nao deixa passar mesmo com nome digitado:
        //if (!editNome.equals("null"))
        //v2: if (!editNome.text.toString().equals(null)) {


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
    // Update via SQLite - não usa para API
    fun update(){
        usuario = Usuario(
            editNome.text.toString(),
            editCelular.text.toString(),
            sexo.selectedItem.toString(),
            editEmail.text.toString(),
            editSenha.text.toString()
        )
        values = ContentValues()
        values.put("nome",usuario.nome)
        values.put("celular",usuario.celular)
        values.put("sexo",usuario.sexo)
        values.put("email",usuario.email)
        values.put("senha",usuario.senha)

        bd.update("tbappbsgi",values,"email", arrayOf(usuario.email))
        Toast.makeText(this,"Atualizado com sucesso",Toast.LENGTH_LONG).show()

    }
    */


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

    /*
    // NÃO USA PARA API:
    fun criarAbrirBD(){
        bd = openOrCreateDatabase("bdappbsgi.db", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS tbappbsgi(nome varchar(60) NOT NULL, celular bigint(11), email varchar(60) NOT NULL, senha varchar(15) NOT NULL)")

    }
    */



}