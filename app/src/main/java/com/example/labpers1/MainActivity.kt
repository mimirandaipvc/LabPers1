package com.example.labpers1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference: SharedPreferences = getSharedPreferences(
            "FILE_1", Context.MODE_PRIVATE)

        val usernameValue =  sharedPreference.getString("PREF_USERNAME", "")
        Log.d("SHARED_PREF_AULA","Found shared pred: $usernameValue")

        if(usernameValue!!.isNotEmpty()){
            showWelcome(usernameValue)
        }
    }

    private fun showWelcome(usernameValue: String?) {
        findViewById<TextView>(R.id.tv1).text = "Hello, $usernameValue"
        findViewById<EditText>(R.id.et1).isEnabled = false
        val btn = findViewById<Button>(R.id.btn1)
        btn.text = "Logout"
        btn.setOnClickListener {
            logout(it)
        }
    }

   private  fun logout(view: View) {

       val sharedPreference: SharedPreferences = getSharedPreferences( "FILE_1", Context.MODE_PRIVATE)
       sharedPreference.edit().clear().apply()
       showLogin()

       Log.d("SHARED_PREF_AULA","Found shared pred: ")



   }

    private fun showLogin() {
        findViewById<TextView>(R.id.tv1).text = "Login"
        findViewById<EditText>(R.id.et1).isEnabled = true
        val btn = findViewById<Button>(R.id.btn1)
        btn.text = "Login"
        btn.setOnClickListener {
            login(it)
        }

    }


    fun login(view: View) {

       val editTextUsername = findViewById<EditText>(R.id.et1)

       if(editTextUsername.text.isNotEmpty()){
           val sharedPreference: SharedPreferences = getSharedPreferences( "FILE_1", Context.MODE_PRIVATE)
           sharedPreference.edit()
               .putString("PREF_USERNAME", editTextUsername.text.toString())
               .apply()

           showWelcome(editTextUsername.text.toString())
           Log.d("SHARED_PREF_AULA","Found shared pred: ${editTextUsername.text}")
       }
   }




}