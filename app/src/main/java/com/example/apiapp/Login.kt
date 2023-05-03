package com.example.apiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var  enterButton: Button
    private lateinit var  loginUser: EditText
    private lateinit var  loginPass: EditText
    private lateinit var  dbh: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enterButton = findViewById(R.id.enterButton)
        loginUser = findViewById(R.id.etLoginUser)
        loginPass = findViewById(R.id.etLoginPass)
        dbh = DBHelper(this)

        enterButton.setOnClickListener{
            val userText = loginUser.text.toString()
            val passText = loginPass.text.toString()

            if(TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText)){
                Toast.makeText(this, "Preencha os dados corretamente", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkUser = dbh.checkuserpass(userText, passText)
                if(checkUser == true){
                    Toast.makeText(this, "Autenticação realizada com sucesso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, PokedexMain::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}