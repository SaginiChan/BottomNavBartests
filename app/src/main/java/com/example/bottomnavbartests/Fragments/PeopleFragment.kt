package com.example.bottomnavbartests.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.Gravity.apply
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.bottomnavbartests.Authentications.LogInActivity
import com.example.bottomnavbartests.MainActivity
import com.example.bottomnavbartests.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_people.view.*

class PeopleFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view= inflater.inflate(R.layout.fragment_people, container, false)

        auth = FirebaseAuth.getInstance()

        view.apply {

            loggouttbutton.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
               val intent =Intent (activity, LogInActivity::class.java)
                activity?.startActivity(intent)


            }

        }
        return view
            }
        }



