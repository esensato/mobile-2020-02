package com.mobile.vencarro

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

// cria o adapter baseado no holder
class VeiculoAdapter (contexto:Context) : RecyclerView.Adapter<VeiculoHolder>(){

    // URL para as marcas dos veiculos
    val URL_MARCA = "https://fipeapi.appspot.com/api/1/carros/marcas.json"

    // URL para os modelos dos veiculos de uma marca (passar o id da marca)
    // por exemplo, FIAT é marca id 21 então a URL fica:
    // https://fipeapi.appspot.com/api/1/carros/veiculos/21.json
    val URL_MODELO = "https://fipeapi.appspot.com/api/1/carros/veiculos/"

    // lista que contera os dados dos veiculos
    var listaVeiculos = ArrayList<Veiculo>()

    // declara a fila de requisições do Volley
    var filaRequisicao: RequestQueue

    // bloco executado quando a classe é instanciada - iniciaizador
    init {

        filaRequisicao = Volley.newRequestQueue(contexto)
        obterMarcas()
    }

    // instancia o VeiculoHolder para cada item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiculoHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.linha_lista_veiculo, parent, false)
        return VeiculoHolder(view)
    }

    // total de itens na lista
    override fun getItemCount(): Int {
        return listaVeiculos.size
    }

    override fun onBindViewHolder(holder: VeiculoHolder, position: Int) {

        // define os valores (dados) a serem exibidos na interface da lista
        holder.txtMarca.text = listaVeiculos.get(position).marca
        holder.txtModelo.text = listaVeiculos.get(position).modelo
        holder.txtAno.text = listaVeiculos.get(position).ano

        // associa um evento de click sobre cada uma das linhas da lista
        holder.linha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.i("VENCARRO", "Selecionado: ${listaVeiculos.get(position).idMarca}")
                // efetuar uma nova requisição passando o id da marca para obter os modelos de veiculo
                obterModelos()
            }
        })
    }

    // obtem as marcas de veiculos
    fun obterMarcas() {

        var req = JsonArrayRequest(Request.Method.GET, URL_MARCA, null,
            Response.Listener<JSONArray> { response ->

                Log.i("RESULTADO", response.toString())
                // [{"name": "AUDI", "fipe_name": "Audi", "order": 2, "key": "audi-6", "id": 6}, ...]
                for (i in 0 until response.length()) {
                val obj = response.getJSONObject(i)
                listaVeiculos.add(Veiculo(obj.getString("id"),
                    obj.getString("name"),
                    "-", "-", "-", "-", ""))
                }
                // avisa que a lista de veiculos foi atualizada e portando o RecyclerView deve ser redesenhado
                notifyDataSetChanged()
            },
            Response.ErrorListener { error -> Log.e("CARRO", "ERRO: " + error.message) })

        // coloca a requisiçao na fila para processamento pelo Volley
        filaRequisicao.add(req)

    }
}