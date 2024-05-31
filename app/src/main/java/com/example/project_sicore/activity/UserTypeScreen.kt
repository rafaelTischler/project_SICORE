package com.example.project_sicore.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R

class UserTypeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_type_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spannable = SpannableString("Está com dúvida? Deixe-nos ajudar")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            spannable.length - 16,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val txtDuvida: TextView = this.findViewById(R.id.txt_peca_ajuda)
        txtDuvida.text = spannable

        val btnCooperativa: ImageButton = this.findViewById(R.id.btn_cooperativa)
        val btnCatador: ImageButton = this.findViewById(R.id.btn_catador)
        val btnDepositante: ImageButton = this.findViewById(R.id.btn_depositante)

        btnCooperativa.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
        btnCatador.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
        btnDepositante.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }

    }
}