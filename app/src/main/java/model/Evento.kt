package model

class Evento(var idEvento: Int,var idOrganizacao:Int, var idTipoEvento:Int,var Titulo:String,
             var dataEvento:String,var cepEvento:String,var idCidadeEvento:Int,var logradouroEvento:String,
             var numEvento:String,var complementoEvento:String,var bairroEvento:String){

    override fun toString(): String {
        return ""
    }
}