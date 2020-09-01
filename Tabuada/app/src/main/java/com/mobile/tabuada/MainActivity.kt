package com.mobile.tabuada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var num1 = 0;
    var num2 = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1 = Random.nextInt(1, 9)
        num2 = Random.nextInt(1, 9)

        txtNumero1.text = num1.toString()
        txtNumero2.text = num2.toString()
        rbPontos.numStars = 5
        rbPontos.rating = 0.0f


    }

    fun verificar(view:View) {


        val numeroInformado = edtResultado.text.toString()

        if (numeroInformado.toInt() == (num1 * num2)) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Resultado")
            builder.setMessage("Acertou!")
            builder.setNeutralButton("OK") { dialog, which -> dialog.cancel() }
            builder.show()



            rbPontos.rating = rbPontos.rating + 1.0f

        }

    }
}
