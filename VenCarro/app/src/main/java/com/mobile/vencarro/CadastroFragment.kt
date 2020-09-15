package com.mobile.vencarro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cadastro.*

class CadastroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    // acionado apos a Activity ter sido criada
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvListaSelecao.layoutManager = LinearLayoutManager(context!!)
        // define a implementação do adapter para a lista de veiculos
        rvListaSelecao.adapter = VeiculoAdapter(context!!)

    }

}
