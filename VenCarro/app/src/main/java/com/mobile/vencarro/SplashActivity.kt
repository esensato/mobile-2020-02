package com.mobile.vencarro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // oculta o action bar
        // !! impedede que a instrução seja executada caso supportActionBar == null
        // correspondente a:
        // if (supportActionBar != null) { supportActionBar.hide() }
        supportActionBar!!.hide()
        // coloca a activity em modo tela cheia
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // temporizador antes de finalizar a SplashActivity
        val handler = Handler()
        // aguarda por 5 segundos ate executar
        handler.postDelayed(Runnable {
            iniciar()
        }, 5000)

    }

    fun iniciar() {
        // cria um remetente / destinatario usando Intent
        val iniciar = Intent(this, PrincipalActivity::class.java)
        // aciona a activity PrincipalActivity...
        startActivity(iniciar)
        // finaliza SplashActivity
        finish()
    }

}
