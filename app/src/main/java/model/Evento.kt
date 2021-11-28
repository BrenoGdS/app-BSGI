package model


class Evento {
    var idevento: Int = 0
    var organizacao: Int = 0
    var idtipoevento: Int = 0
    lateinit var titulo: String
    lateinit var dataevento: String
    var cepevento: Int = 0
    var idcidadeevento: Int = 0
    lateinit var logradouroevento: String
    var numevento: Int = 0
    lateinit var complementoevento: String
    lateinit var bairroevento: String

    override fun toString(): String {
        return "Evento(idevento=$idevento, organizacao=$organizacao, idtipoevento=$idtipoevento, titulo='$titulo', dataevento='$dataevento', cepevento=$cepevento, idcidadeevento=$idcidadeevento, logradouroevento='$logradouroevento', numevento=$numevento, complementoevento='$complementoevento', bairroevento='$bairroevento')"
    }


}


