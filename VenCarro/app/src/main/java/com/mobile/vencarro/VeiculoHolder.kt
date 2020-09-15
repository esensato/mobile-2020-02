package com.mobile.vencarro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.linha_lista_veiculo.view.*

// cria variaveis locais para acessar os elementos da interface grafica
class VeiculoHolder (item:View) : RecyclerView.ViewHolder(item){

    //criar as variaveis locais mapeando cada elemento da lista definida em linha_lista_veiculo.xml

    var txtMarca:TextView = item.txtMarca
    var txtModelo:TextView = item.txtModelo
    var txtAno:TextView = item.txtAno

}