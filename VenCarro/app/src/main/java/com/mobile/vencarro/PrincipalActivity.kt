package com.mobile.vencarro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        supportFragmentManager.
        beginTransaction().
        add(R.id.flPrincipal, CadastroFragment()).
        commit()

    }

    // exibe o fragmento com o resumo: marca, modelo, ano e preço
    fun exibirResumo(marca:String, modelo:String, ano:String, preco:String) {

        // parametros para serem repassados ao ResumoFragment
        val params = Bundle()
        params.putString("marca", marca)
        params.putString("modelo", modelo)
        params.putString("ano", ano)
        params.putString("preco", preco)

        var resumoFragment = ResumoFragment()
        resumoFragment.arguments = params
        supportFragmentManager.
        beginTransaction().
        replace(R.id.flPrincipal, resumoFragment). // no caso será a troca do fragment (replacement)
        commit()

    }
}
