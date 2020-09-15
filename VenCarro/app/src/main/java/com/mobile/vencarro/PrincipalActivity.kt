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
}
