package com.example.project_sicore.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R

class FormLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtTelaCadastro: TextView = this.findViewById(R.id.txt_tela_cadastro)
        val txtTelaEsqueceu: TextView = this.findViewById(R.id.txt_forgotPass)

        //Função que define apenas o "Cadastre-se" como negrito ao iniciar a Activity
        val spannable = SpannableString("Ainda não possui uma conta? Cadastre-se")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            spannable.length - 11,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //Função que cria o link do TextView para a Activity de Cadastro
        txtTelaCadastro.text = spannable
        txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
        //Função que cria o link do TextView para a Activity de Esqueceu a Senha
        txtTelaEsqueceu.setOnClickListener {
            val intent = Intent(this, FormForgotPassword::class.java)
            startActivity(intent)
        }
    }
}