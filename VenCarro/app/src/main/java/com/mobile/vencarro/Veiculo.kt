package com.mobile.vencarro

// classe para conter os dados dos veiculos obtidos do servico FIPE
data class Veiculo (var idMarca:String, var marca:String,
                    var idModelo:String, var modelo:String,
                    var idAno:String, var ano:String,
                    var preco:String){
}