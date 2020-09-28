package com.mobile.vencarro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_resumo.*
import kotlinx.android.synthetic.main.fragment_resumo.view.*

class ResumoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_resumo, container, false)

        // le os parametros para serem exibidos na tela
        v.txtMarcaResumo.text = arguments!!.getString("marca")
        v.txtModeloResumo.text = arguments!!.getString("modelo")
        v.txtAnoResumo.text = arguments!!.getString("ano")
        v.txtPrecoResumo.text = arguments!!.getString("preco")
        return v
    }


}



