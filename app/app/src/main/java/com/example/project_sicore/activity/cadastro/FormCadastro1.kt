package com.example.project_sicore.activity.cadastro

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
import android.util.Log
import android.widget.EditText
import com.example.project_sicore.activity.login.FormLogin
import com.example.project_sicore.databinding.ActivityFormCadastro1Binding
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest

class FormCadastro1 : AppCompatActivity() {

    private lateinit var usuario : UsuarioCadastroRequest
    private lateinit var binding: ActivityFormCadastro1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_cadastro1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuario = intent.getParcelableExtra("usuario") ?: UsuarioCadastroRequest()
        binding = ActivityFormCadastro1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Função que define apenas o "Login" como negrito ao iniciar a Activity
        val spannable = SpannableString("Já possui uma conta? Login")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            spannable.length - 5,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //Função que cria o link do TextView para a Activity de Login
        binding.txtTelaLogin.text = spannable
        binding.txtTelaLogin.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
        binding.btnVoltar.setOnClickListener {
            this.finish();
        }
        binding.btnProximo.setOnClickListener {
            val intent = Intent(this, FormCadastro2::class.java)
            pegarDadosInput();
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }

    private fun pegarDadosInput(){
        val email = binding.editCreateEmail.text.toString()
        val confEmail = binding.editConfirmEmail.text.toString()
        val senha = binding.editCreatePassword.text.toString()
        val confSenha =binding.editConfirmPassword.text.toString()
        //validar Dados
        usuario.email = email
        usuario.senha = senha
    }
}