package com.example.project_sicore.activity.cadastro

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project_sicore.R
import com.example.project_sicore.activity.login.FormLogin
import com.example.project_sicore.activity.temp.TELAMOSTRARRESULTADO
import com.example.project_sicore.databinding.ActivityFormCadastro2Binding
import com.example.project_sicore.utils.apis.crud.CrudService
import com.example.project_sicore.utils.apis.viaCep.ViaCepService
import com.example.project_sicore.utils.modelos.EnderecoViaCepResponse
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FormCadastro2 : AppCompatActivity() {

    private lateinit var usuario: UsuarioCadastroRequest
    private lateinit var binding: ActivityFormCadastro2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_cadastro2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuario = intent.getParcelableExtra("usuario") ?: UsuarioCadastroRequest()
        binding = ActivityFormCadastro2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            this.finish()
        }

        binding.editCep.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(textoCep: Editable?) {}
            override fun beforeTextChanged(
                textoCep: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                textoCep: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (textoCep?.length == 8) {
                    lifecycleScope.launch {
                        try {
                            val apiViaCep = ViaCepService()
                            val response = apiViaCep.buscaCep(textoCep.toString())
                            if (response.isSuccessful) {
                                Log.d("APIV", "Sucesso: ${response.body()}")
                                val endereco = response.body()
                                preencherInputs(endereco!!)

                            } else {
                                Log.e("APIV", "Erro: ${response.errorBody()?.string()}")
                            }
                        } catch (e: HttpException) {
                            Log.e("APIV", "HttpException: ${e.message()}")
                        } catch (e: Exception) {
                            Log.e("APIV", "Exception: ${e.message}")
                        }
                    }

                } else {
                    // Mostrar cep invalido
                }
            }
        })


        binding.btnCadastrar.setOnClickListener {
            pegarDadosInputs()
            cadastrar()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)

        }
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
    }

    private fun cadastrar() {
        lifecycleScope.launch {
            try {
                val res = comunicaApi()
                if (res.isSuccessful) {
                    Log.d("APIV", "Sucesso: ${res.body()}")
                    // Navegar para a próxima tela ou exibir uma mensagem de sucesso
                    val intent = Intent(this@FormCadastro2, TELAMOSTRARRESULTADO::class.java)
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
                } else {
                    Log.e("APIV", "Erro: ${res.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                Log.e("APIV", "HttpException: ${e.message()}")
            } catch (e: Exception) {
                Log.e("APIV", "Exception: ${e.message}")
            }
        }
    }

    private fun pegarDadosInputs() {
        val cep = binding.editCep.text.toString()
        val pais = binding.editPais.text.toString()
        val estado = binding.editEstado.text.toString()
        val cidade = binding.editCidade.text.toString()
        val bairro = binding.editBairro.text.toString()
        val rua = binding.editRua.text.toString()

        usuario.endereco.cep = cep
        usuario.endereco.pais = pais
        usuario.endereco.estado = estado
        usuario.endereco.cidade = cidade
        usuario.endereco.bairro = bairro
        usuario.endereco.rua = rua
    }

    private suspend fun comunicaApi(): retrofit2.Response<Any> {
        val crud = CrudService()
        return crud.cadastrarUsuario(usuario)
    }

    private fun preencherInputs(endereco: EnderecoViaCepResponse) {
        val cepEditText = binding.editCep
        val paisEditText = binding.editPais
        val estadoEditText = binding.editEstado
        val cidadeEditText = binding.editCidade
        val bairroEditText = binding.editBairro
        val ruaEditText = binding.editRua
        if (endereco.cep != "") {
            cepEditText.text = Editable.Factory.getInstance().newEditable(endereco.cep)
        }

        paisEditText.text = Editable.Factory.getInstance().newEditable("Brasil")

        if (endereco.uf != "") {
            estadoEditText.text = Editable.Factory.getInstance().newEditable(endereco.uf)
        }
        if (endereco.localidade != "") {
            cidadeEditText.text = Editable.Factory.getInstance().newEditable(endereco.localidade)
        }
        if (endereco.bairro != "") {
            bairroEditText.text = Editable.Factory.getInstance().newEditable(endereco.bairro)
        }
        if (endereco.logradouro != "") {
            ruaEditText.text = Editable.Factory.getInstance().newEditable(endereco.logradouro)
        }
    }
}
