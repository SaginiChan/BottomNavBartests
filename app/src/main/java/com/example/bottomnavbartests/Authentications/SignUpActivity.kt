package com.example.bottomnavbartests.Authentications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.bottomnavbartests.R
import com.example.bottomnavbartests.R.layout.activity_sign_up
import com.example.bottomnavbartests.R.layout.fui_phone_progress_dialog
import com.example.bottomnavbartests.Util.FirestoreUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_sign_up)

        auth = FirebaseAuth.getInstance()

        signupbutton.setOnClickListener {
            signUpUser()
        }
    }
    private fun signUpUser() {
        if (signuptv.text.toString().isEmpty()) {
            signuptv.error = "Please enter email"
            signuptv.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(signuptv.text.toString()).matches()) {
            signuptv.error = "Please enter valid email"
            signuptv.requestFocus()
            return
        }

        if (signupPassword.text.toString().isEmpty()) {
            signupPassword.error = "Please enter password"
            signupPassword.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(signuptv.text.toString(),  signupPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    FirestoreUtil.InitCurrentUserIfFirsttime {
                        startActivity(Intent(this, LogInActivity::class.java))
                        finish()
                    }

                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }


}