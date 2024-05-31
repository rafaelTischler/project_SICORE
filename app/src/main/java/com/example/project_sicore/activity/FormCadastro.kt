package com.example.project_sicore.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R

class FormCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Variável para armazenar o TextView da referência para a tela de Login
        val txtTelaLogin: TextView = this.findViewById(R.id.txt_tela_login)
        //Variável para armazenar o Button de Próximo
        val btnEtapa2: Button = this.findViewById(R.id.btn_proximo)
        //Variável para armazenar o Button de Voltar
        val btnVoltarLogin: Button = this.findViewById(R.id.btn_voltar_login)

        //Função que define apenas o "Login" como negrito ao iniciar a Activity
        val spannable = SpannableString("Já possui uma conta? Login")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            spannable.length - 5,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //Função que cria o link do TextView para a Activity de Login
        txtTelaLogin.text = spannable
        txtTelaLogin.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
        //Função que permite prosseguir pra proxima etapa do Cadastro
        btnEtapa2.setOnClickListener {
            val intent = Intent(this, FormCadastro1::class.java)
            startActivity(intent)
        }
        //Função que permite voltar à tela de Login
        btnVoltarLogin.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
    }
}