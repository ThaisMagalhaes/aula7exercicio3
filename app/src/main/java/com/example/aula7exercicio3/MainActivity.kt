package com.example.aula7exercicio3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import androidx.core.view.ViewPropertyAnimatorListenerAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telefone = findViewById<ExtendedFloatingActionButton>(R.id.telefone)
        val navegador = findViewById<ExtendedFloatingActionButton>(R.id.navegador)
        val mensagem = findViewById<ExtendedFloatingActionButton>(R.id.mensagem)
        val email = findViewById<ExtendedFloatingActionButton>(R.id.email)

        setupButtonClickAnimation(telefone)
        setupButtonClickAnimation(navegador)
        setupButtonClickAnimation(mensagem)
        setupButtonClickAnimation(email)

        telefone.setOnClickListener {
            val intentChamadas = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + "5555555555")
            }
            startActivity(intentChamadas)
        }

        navegador.setOnClickListener {
            val intentNavegador = Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"))
            startActivity(intentNavegador)
        }

        mensagem.setOnClickListener {
            val intentSMS = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("sms:" + "5555555555")
                putExtra("sms_body", "Pós-Graduação!")
            }
            startActivity(intentSMS)
        }

        email.setOnClickListener {
            val intentEmail = Intent(Intent.ACTION_SEND)
            intentEmail.data = Uri.parse("mailto:")
            intentEmail.type = "message/rfc822"
            intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf("testesThais@teste.com", "testeThais2@teste.com"));
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
            intentEmail.putExtra(Intent.EXTRA_TEXT, "Corpo");
            startActivity(intentEmail)
        }
    }

    private fun setupButtonClickAnimation(button: ExtendedFloatingActionButton) {
        button.setOnClickListener {
            animateButtonClick(button)
        }
    }

    //Essa animação faz com que o botão seja reduzido em escala quando pressionado e retorne ao tamanho original quando o clique é liberado.
    private fun animateButtonClick(button: ExtendedFloatingActionButton) {
        val scaleX = 0.9f
        val scaleY = 0.9f
        val duration = 150L

        val scaleXAnimator: ViewPropertyAnimatorCompat =
            ViewCompat.animate(button).scaleX(scaleX).setDuration(duration)
        val scaleYAnimator: ViewPropertyAnimatorCompat =
            ViewCompat.animate(button).scaleY(scaleY).setDuration(duration)

        scaleXAnimator.setListener(
            object : ViewPropertyAnimatorListenerAdapter() {
                override fun onAnimationEnd(view: View) {
                    ViewCompat.animate(button).scaleX(1.0f).scaleY(1.0f).setDuration(duration).start()
                }
            }
        )

        scaleXAnimator.start()
        scaleYAnimator.start()
    }
}
