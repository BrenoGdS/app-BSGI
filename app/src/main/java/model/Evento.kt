package model


class Evento {
    var idevento: Int = 0
    var idtipoevento: Int = 0
    lateinit var titulo: String
    lateinit var dataevento: String
    var cepevento: Int = 0
    var idcidadeevento: Int = 0
    lateinit var logradouroevento: String
    var numevento: Int = 0
    lateinit var complementoevento: String
    lateinit var bairroevento: String
    lateinit var desctipoEvento: String
    lateinit var nomeOrg: String
    lateinit var descCidade: String

    constructor(
        idevento: Int,
        idtipoevento: Int,
        titulo: String,
        dataevento: String,
        cepevento: Int,
        idcidadeevento: Int,
        logradouroevento: String,
        numevento: Int,
        complementoevento: String,
        bairroevento: String,
        desctipoEvento: String,
        nomeOrg: String,
        descCidade: String
    ) {
        this.idevento = idevento
        this.idtipoevento = idtipoevento
        this.titulo = titulo
        this.dataevento = dataevento
        this.cepevento = cepevento
        this.idcidadeevento = idcidadeevento
        this.logradouroevento = logradouroevento
        this.numevento = numevento
        this.complementoevento = complementoevento
        this.bairroevento = bairroevento
        this.desctipoEvento = desctipoEvento
        this.nomeOrg = nomeOrg
        this.descCidade = descCidade
    }

    constructor(titulo: String, nomeOrg: String, desctipoEvento: String, descCidade: String, dataevento: String) {
        this.titulo = titulo
        this.nomeOrg = nomeOrg
        this.desctipoEvento = desctipoEvento
        this.descCidade = descCidade
        this.dataevento = dataevento
    }

    //construtor delete
    constructor(idevento: Int){
        this.idevento = idevento
    }

    //construtor update
    constructor(titulo:String, nomeOrg:String, desctipoEvento:String, dataevento:String, cepevento:Int, descCidade:String,
                logradouroevento:String, numevento:Int, complementoevento:String, bairroevento:String, idevento:Int) {
        this.titulo = titulo
        this.nomeOrg = nomeOrg
        this.desctipoEvento = desctipoEvento
        this.dataevento = dataevento
        this.cepevento = cepevento
        this.descCidade = descCidade
        this.logradouroevento = logradouroevento
        this.numevento = numevento
        this.complementoevento = complementoevento
        this.bairroevento = bairroevento
        this.idevento = idevento
    }
    //this.idtipoevento = idtipoevento




        //construtor teste
    constructor(titulo: String,nomeOrg: String,desctipoEvento: String,logradouroevento: String,complementoevento: String,bairroevento: String,){
        this.titulo = titulo
        this.nomeOrg = nomeOrg
        this.desctipoEvento = desctipoEvento
        this.logradouroevento = logradouroevento
        //this.numevento = numevento
        this.complementoevento = complementoevento
        this.bairroevento = bairroevento
    }

    //construtor teste2
    constructor(titulo: String,nomeOrg: String,desctipoEvento: String, cepevento: Int,logradouroevento: String,complementoevento: String,bairroevento: String,){
        this.titulo = titulo
        this.nomeOrg = nomeOrg
        this.desctipoEvento = desctipoEvento
        this.cepevento = cepevento
        this.logradouroevento = logradouroevento
        //this.numevento = numevento
        this.complementoevento = complementoevento
        this.bairroevento = bairroevento
    }




    override fun toString(): String {
        return "$titulo - $desctipoEvento ($nomeOrg) $dataevento | End.: $logradouroevento, $numevento, $complementoevento, $bairroevento - $cepevento | $descCidade"
    }


}


