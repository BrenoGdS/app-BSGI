package model


// NÃO USA PARA API:
/*
class Usuario(var nome:String, var celular:String, var sexo:String, var email:String, var senha:String){
    override fun toString(): String {
        return "Usuario:  $email cadastrado"
    }
}
*/


class Usuario {
    var id:Int=0
    lateinit var nome: String
    var celular: Int=0
    lateinit var sexo: String
    lateinit var email:String
    lateinit var senha:String


    //addUsuario
    constructor(nome: String, celular: Int, sexo: String, email: String, senha: String) {
        this.nome = nome
        this.celular = celular
        this.sexo = sexo
        this.email = email
        this.senha = senha
    }


    //updateUsuario
    constructor(
        id: Int,
        nome: String,
        celular: Int,
        sexo: String,
        email: String,
        senha: String
    ) {
        this.id = id
        this.nome = nome
        this.celular = celular
        this.sexo = sexo
        this.email = email
        this.senha = senha
    }

    // deleteUsuario
    constructor(id: Int) {
        this.id = id
    }

    // getUsuario - validar usuário
    constructor(email: String, senha:String) {
        this.email = email
        this.senha = senha
    }

    // getUsuario - consultar email
    constructor(email: String) {
        this.email = email
    }


    // temp
    constructor(id:Int, email: String,nome: String){
        this.id = id
        this.email = email
        this.nome  = nome
    }


    override fun toString(): String {
        return "USUÁRIO: (id=$id, nome='$nome', celular=$celular, sexo='$sexo', email='$email', senha='$senha')"
    }


}

