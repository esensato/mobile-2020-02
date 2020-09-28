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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

// cria o adapter baseado no holder
class VeiculoAdapter (contexto_:Context) : RecyclerView.Adapter<VeiculoHolder>(){

    // armazena o contexto que acionou o Adapter (PrincipalActivity)
    var contexto = contexto_
    // armazenara os valores selecionados (marca, modelo e ano)
    var idMarca:String = ""
    var marca:String = ""
    var idModelo:String = ""
    var modelo:String = ""
    var idAno:String = ""
    var ano:String = ""
    // indica aqual o tipo de requisição efetuar: MODELO, ANO e PRECO
    var tipoRequisicao:String = "MODELO"

    // URL para as marcas dos veiculos
    val URL_MARCA = "https://fipeapi.appspot.com/api/1/carros/marcas.json"

    // URL para os modelos dos veiculos de uma marca (passar o id da marca)
    // por exemplo, FIAT é marca id 21 então a URL fica:
    // https://fipeapi.appspot.com/api/1/carros/veiculos/21.json
    val URL_MODELO = "https://fipeapi.appspot.com/api/1/carros/veiculos/"

    // URL para obter o ano / combustivel dos veiculos dentro do modelo selecionado
    val URL_ANO = "https://fipeapi.appspot.com/api/1/carros/veiculo/"

    // obtem o preco de um modelo de veiculo informando seu ano e combistivel
    val URL_PRECO = "https://fipeapi.appspot.com/api/1/carros/veiculo/"

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
                // armazena o id da marca selecionada na variavel de escopo global idMarca
                idMarca = listaVeiculos.get(position).idMarca
                marca = listaVeiculos.get(position).marca
                idModelo = listaVeiculos.get(position).idModelo
                modelo = listaVeiculos.get(position).modelo
                idAno = listaVeiculos.get(position).idAno
                ano = listaVeiculos.get(position).ano

                // efetuar uma nova requisição passando o id da marca para obter os modelos de veiculo
                if (tipoRequisicao == "MODELO") {
                    obterModelos()
                    tipoRequisicao = "ANO"
                } else if (tipoRequisicao == "ANO"){
                    obterAnos()
                    tipoRequisicao = "PRECO"
                } else if (tipoRequisicao == "PRECO"){
                    obterPreco()
                }
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

    // obtem os modelos de veículo de acordo com a marca
    fun obterModelos() {

        var req = JsonArrayRequest(Request.Method.GET, URL_MODELO + idMarca + ".json", null,
            Response.Listener<JSONArray> { response ->

                Log.i("RESULTADO", response.toString())
                // [{"fipe_marca": "Fiat", "name": "147 C/ CL", "marca": "FIAT", "key": "147-437", "id": "437", "fipe_name": "147 C/ CL"}...
                // limpa a lista de veiculos antes de atualizar
                listaVeiculos.clear()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    listaVeiculos.add(Veiculo(idMarca,
                        obj.getString("marca"),
                        obj.getString("id"), obj.getString("name"), "-", "-", ""))
                }
                // avisa que a lista de veiculos foi atualizada e portando o RecyclerView deve ser redesenhado
                notifyDataSetChanged()
            },
            Response.ErrorListener { error -> Log.e("CARRO", "ERRO: " + error.message) })

        // coloca a requisiçao na fila para processamento pelo Volley
        filaRequisicao.add(req)

    }

    // obter o ano do modelo do veiculo dentro da marca
    fun obterAnos() {

        var req = JsonArrayRequest(Request.Method.GET, URL_ANO + idMarca + "/" + idModelo + ".json", null,
            Response.Listener<JSONArray> { response ->

                Log.i("RESULTADO", response.toString())
                // [{"fipe_codigo": "32000-1", "name": "Zero KM Gasolina", "key": "32000-1", "veiculo": "Palio 1.0 ECONOMY Fire Flex 8V 4p", "id": "32000-1"},...
                // limpa a lista de veiculos antes de atualizar
                listaVeiculos.clear()
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    listaVeiculos.add(Veiculo(idMarca,
                        marca,
                        idModelo,
                        modelo,
                        obj.getString("fipe_codigo"), obj.getString("name"), ""))
                }
                // avisa que a lista de veiculos foi atualizada e portando o RecyclerView deve ser redesenhado
                notifyDataSetChanged()
            },
            Response.ErrorListener { error -> Log.e("CARRO", "ERRO: " + error.message) })

        // coloca a requisiçao na fila para processamento pelo Volley
        filaRequisicao.add(req)

    }

    // obter o preco do veiculo
    fun obterPreco() {

        var req = JsonObjectRequest(Request.Method.GET, URL_PRECO + idMarca + "/" + idModelo + "/" + idAno + ".json", null,
            Response.Listener<JSONObject> { response ->

                Log.i("RESULTADO", response.toString())
                Log.i("PRECO", response.getString("preco"))
                (contexto as PrincipalActivity).exibirResumo(marca, modelo, ano, response.getString("preco"))

            },
            Response.ErrorListener { error -> Log.e("CARRO", "ERRO: " + error.message) })

        // coloca a requisiçao na fila para processamento pelo Volley
        filaRequisicao.add(req)
    }
}