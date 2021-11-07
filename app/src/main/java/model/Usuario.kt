package model

class Usuario(var nome:String, var celular:String, var email:String, var senha:String){
    override fun toString(): String {
        return "Usuario:  $email cadastrado"
    }
}