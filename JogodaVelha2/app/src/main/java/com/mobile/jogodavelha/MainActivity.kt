package com.mobile.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    // armazena de quem é a vez "X" ou "O"
    var vez = "X"
    // representa as posicoes no tabuleiro
    var tabuleiro = arrayOf<String>("", "", "",
                                    "", "", "",
                                    "", "", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cliqueBotao (quem:View){

        // Exibe uma janela com texto por determinado tempo
        //Toast.makeText(this, "Botao ${quem.id} clicado", Toast.LENGTH_SHORT).show()
        //converte o gerador do evento para Button e altera o texto exibido (propriedade text)
        (quem as Button).text = vez

        // mapear o botao clicado para uma posicao do array que representa o tabuleiro...
        var pos = 0
        if (quem.id == R.id.btn11) {
            pos = 0 // primeira posicao do tabuleiro
        } else if (quem.id == R.id.btn12) {
            pos = 1
        } else if (quem.id == R.id.btn13) {
            pos = 2 // primeira posicao do tabuleiro
        }
        // ...

        tabuleiro[pos] = vez

        // gera mensagem de log nível INFO
        Log.i("JOGOVELHA", "Clique no botao...${tabuleiro[0]}, ${tabuleiro[1]}, ${tabuleiro[2]}")

        // analisa se existem vencedores
        // verifica se primeira linha esta preenchida com o mesmo simbolo (X ou O)
        if (tabuleiro[0] == tabuleiro[1] && tabuleiro[1] == tabuleiro[2]) {
            Toast.makeText(this, R.string.vencedor, Toast.LENGTH_LONG).show()
        }
        // ...

        // troca a vez de quem joga
        if (vez == "X") {
            vez = "O"
        } else {
            vez = "X"
        }


    }
}
