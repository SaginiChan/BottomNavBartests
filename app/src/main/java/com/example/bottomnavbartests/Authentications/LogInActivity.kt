package com.example.bottomnavbartests.Authentications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.bottomnavbartests.MainActivity
import com.example.bottomnavbartests.R
import com.example.bottomnavbartests.R.layout.activity_log_in
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_log_in)

        auth = FirebaseAuth.getInstance()
        loginbutton.setOnClickListener {
            doLogin()
        }

        tosignupTV.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
    private fun   doLogin() {
        if (loginEnail.text.toString().isEmpty()) {
            loginEnail.error = "Please enter email"
            loginEnail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(loginEnail.text.toString()).matches()) {
            loginEnail.error = "Please enter valid email"
            loginEnail.requestFocus()
            return
        }

        if (loginPassword.text.toString().isEmpty()) {
            loginPassword.error = "Please enter password"
            loginPassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword( loginEnail.text.toString(), loginPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    Toast.makeText(baseContext, "login failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }


            }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun  updateUI(currentUser: FirebaseUser?){
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(
                baseContext, "Please verify your email address.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}


