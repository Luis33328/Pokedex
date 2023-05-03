package com.example.apiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Signup : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var confPass: EditText
    private lateinit var saveButton: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        username = findViewById(R.id.etSignupUser)
        password = findViewById(R.id.etSignupPass)
        confPass = findViewById(R.id.etSignupConfPass)
        saveButton = findViewById(R.id.saveButton)
        db = DBHelper(this)

        saveButton.setOnClickListener{
            val userText = username.text.toString()
            val passText = password.text.toString()
            val confPassText = confPass.text.toString()
            val saveData = db.insertData(userText, passText)

            if(TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText) || TextUtils.isEmpty(confPassText)){
                Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show()
            }
            else{
                if(passText.equals(confPassText)){
                    if(saveData == true){
                        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Esse usuário já existe", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Confirme sua senha", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}