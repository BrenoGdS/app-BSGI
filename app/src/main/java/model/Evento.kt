package model


class Evento {

    lateinit var organizacao:String
    lateinit var estado:String
    lateinit var data:String
    lateinit var titulo:String
    lateinit var logradouroevento:String
    var idevento: Int = 0

    constructor(organizacao:String,estado:String,data:String,titulo:String,logradouroevento:String){
        this.organizacao = organizacao
        this.estado = estado
        this.data = data
        this.titulo = titulo
        this.logradouroevento = logradouroevento

    }

    constructor(
        idevento: Int,
        organizacao: String,
        estado: String,
        data: String,
        titulo: String,
        logradouroevento: String
    ) {
        this.idevento = idevento
        this.organizacao = organizacao
        this.estado = estado
        this.data = data
        this.titulo = titulo
        this.logradouroevento = logradouroevento
    }
    constructor(idevento:Int){
        this.idevento = idevento
    }

    override fun toString(): String {
        return "Evento(idevento=$idevento, organizacao='$organizacao', estado='$estado', data='$data', titulo='$titulo', logradouroevento='$logradouroevento')"
    }



}