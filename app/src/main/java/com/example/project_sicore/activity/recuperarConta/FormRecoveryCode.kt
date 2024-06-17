package com.example.project_sicore.activity.recuperarConta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project_sicore.R
import com.example.project_sicore.databinding.ActivityFormRecoveryCodeBinding
import com.example.project_sicore.utils.apis.crud.CrudService
import com.example.project_sicore.utils.modelos.RecuperacaoContaRequest
import kotlinx.coroutines.launch
import retrofit2.Response


class FormRecoveryCode : AppCompatActivity() {
    private lateinit var binding: ActivityFormRecoveryCodeBinding
    private lateinit var request : RecuperacaoContaRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_recovery_code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityFormRecoveryCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        request = intent.getParcelableExtra("request") ?: RecuperacaoContaRequest()

        binding.btnEnviar.setOnClickListener {
            val intent = Intent(this, FormRedefinePassword::class.java)
            lifecycleScope.launch {
                val response = enviarSolicitacaoApi()
                if (response.isSuccessful){
                    // Ir para procima etapa
                    intent.putExtra("request",request)
                }else{
                    // Code errado
                }
            }
            startActivity(intent)
        }
    }

    private suspend fun enviarSolicitacaoApi() : Response<Any>{
        val codigo = binding.editInsertCode.text.toString()
        val crud = CrudService()
        request.codigo = codigo
        return crud.verificaCodigo(request);
    }
}