package com.example.project_sicore.activity.cadastro

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
import com.example.project_sicore.R
import com.example.project_sicore.activity.login.FormLogin
import com.example.project_sicore.databinding.ActivityFormCadastroBinding
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Função que define apenas o "Login" como negrito ao iniciar a Activity
        val spannable = SpannableString("Já possui uma conta? Login")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            spannable.length - 5,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.txtTelaLogin.text = spannable
        binding.txtTelaLogin.setOnClickListener(){
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }

        binding.btnProximo.setOnClickListener() {
            val intent = Intent(this, FormCadastro1::class.java)
            val usuario = pegarDadosInput()
            intent.putExtra("usuario",usuario)
            startActivity(intent)
        }

        binding.btnVoltarLogin.setOnClickListener(){
            this.finish();
        }
    }
    private fun pegarDadosInput() : UsuarioCadastroRequest{
        val nome = binding.editUsername.text.toString()
        val cpfOuCnpj = binding.editCpfcnpj.text.toString()
        val dataNascimento = binding.editNascimento.text.toString()
        val telefone = binding.editPhone.text.toString()
        return UsuarioCadastroRequest(nome = nome, cpf = cpfOuCnpj, dataNasci = dataNascimento, telefone = telefone)
    }

}