package com.example.shared_preference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shared_preference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        checkForSharedPref()
    }



    private fun setupUI(){
        binding.loginBtn.setOnClickListener {

            val username= binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            if (username.isBlank() || username.length <5){
                Toast.makeText(this, "Plsese enter a valid username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isBlank() || password.length< 5){
                Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            checkSharedPreference()
            val intent = Intent(this,DashBord::class.java)
            startActivity(intent)
            // finish()
        }
    }

    private fun checkSharedPreference() {
        if(binding.cbSaveInfo.isChecked){
            val username= binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            // Get shared preference
            val sharedPreference =getSharedPreferences("LOGIN_INFO", MODE_PRIVATE)

            //Create editor
            val editor =sharedPreference.edit()

            //Add data
            editor.putString("USERNAME",username)
            editor.putString("PASSWORD",password)

            //Save changes
            editor.commit()
        }
    }
    private fun checkForSharedPref() {
        val sharedPreference =getSharedPreferences("LOGIN_INFO", MODE_PRIVATE)
        val username =sharedPreference.getString("USERNAME","")
        val password =sharedPreference.getString("PASSWORD","")

        if(!username.isNullOrBlank()){
            binding.txtUsername.setText(username)
        }
        if(!password.isNullOrBlank()){
            binding.txtPassword.setText(password)
        }

    }
}