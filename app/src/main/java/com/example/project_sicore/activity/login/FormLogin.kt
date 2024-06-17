package com.example.project_sicore.activity.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project_sicore.R
import com.example.project_sicore.activity.cadastro.FormCadastro
import com.example.project_sicore.activity.recuperarConta.FormForgotPassword
import com.example.project_sicore.databinding.ActivityFormCadastroBinding
import com.example.project_sicore.databinding.ActivityFormLoginBinding
import com.example.project_sicore.utils.apis.crud.CrudService
import com.example.project_sicore.utils.modelos.UsuarioLoginRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Função que define apenas o "Cadastre-se" como negrito ao iniciar a Activity
        val spannable = SpannableString("Ainda não possui uma conta? Cadastre-se")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            spannable.length - 11,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.txtForgotPass.setOnClickListener {

            val intent = Intent(this, FormForgotPassword::class.java)
            startActivity(intent)

        }

        binding.btnEntrar.setOnClickListener() {
            val response = logar()
            if (response.equals("OK")) {
                //Mostra mapa
            } else {
                // Mostra erro
            }
        }
    }

    private fun logar() {
        val usuario = pegarDadosInput()
        lifecycleScope.launch {
            val resposta = comunicaApi(usuario)
            if (resposta.equals("OK")) {
                // logar
            } else {
                // Tratar erro
            }
        }

    }

    private fun pegarDadosInput(): UsuarioLoginRequest {
        val email = binding.editEmail.text.toString()
        val senha = binding.editPassword.text.toString()
        //validar email
        return UsuarioLoginRequest(email, senha)
    }

    private suspend fun comunicaApi(usuario: UsuarioLoginRequest): Response<Any> {
        val crud = CrudService()
        return crud.logarUsuario(usuario)
    }
}