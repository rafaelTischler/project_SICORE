package com.example.project_sicore.activity.recuperarConta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project_sicore.R
import com.example.project_sicore.databinding.ActivityFormRecoveryCodeBinding
import com.example.project_sicore.databinding.ActivityFormRedefinePasswordBinding
import com.example.project_sicore.utils.apis.crud.CrudService
import com.example.project_sicore.utils.modelos.RecuperacaoContaRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class FormRedefinePassword : AppCompatActivity() {
    private lateinit var binding: ActivityFormRedefinePasswordBinding
    private lateinit var request: RecuperacaoContaRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_redefine_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFormRedefinePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        request = intent.getParcelableExtra("request") ?: RecuperacaoContaRequest()

        binding.btnEnviar.setOnClickListener(){
            lifecycleScope.launch {
                val response = enviarSolicitacaoApi()
                if (response.isSuccessful){
                    // Mostrar ok e ir para tela de login
                }else{
                    // sei la, mostrar erro
                }
            }
        }
    }

    private suspend fun enviarSolicitacaoApi() : Response<Any>{
        val senha = binding.editRedefinePassword.text.toString()
        val confSenha = binding.editConfirmPassword.text.toString()
        // Validar dados
        val crud = CrudService()
        request.novaSenha = senha
        return crud.atualizaSenha(request)
    }
}