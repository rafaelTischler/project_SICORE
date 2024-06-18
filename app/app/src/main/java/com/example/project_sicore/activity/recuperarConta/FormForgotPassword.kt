package com.example.project_sicore.activity.recuperarConta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project_sicore.R
import com.example.project_sicore.databinding.ActivityFormCadastroBinding
import com.example.project_sicore.databinding.ActivityFormForgotPasswordBinding
import com.example.project_sicore.utils.apis.crud.CrudService
import com.example.project_sicore.utils.modelos.RecuperacaoContaRequest
import kotlinx.coroutines.launch
import retrofit2.Response

//import com.example.sicore.crud

class FormForgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityFormForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_forgot_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityFormForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
        binding.btnEnviar.setOnClickListener {
            val intent = Intent(this, FormRecoveryCode::class.java)
            lifecycleScope.launch {
                val response = enviarRequisicaoApi()
                if (response.isSuccessful){
                    val request = RecuperacaoContaRequest()
                    //request.email = response.body().email
                    intent.putExtra("request",request)
                    // Ir para proxima etapa
                }else{
                    // Email invalido
                }
            }
            startActivity(intent)
        }


    }

    private suspend fun enviarRequisicaoApi(): Response<Any>{
        var email = binding.editInsertEmail.text.toString()
        val crud = CrudService()
        return crud.verificarEmail(email)
    }
}