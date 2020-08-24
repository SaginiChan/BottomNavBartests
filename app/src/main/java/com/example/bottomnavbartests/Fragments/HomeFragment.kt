package com.example.bottomnavbartests.Fragments

import android.app.Person
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bottomnavbartests.Models.User
import com.example.bottomnavbartests.R
import com.example.bottomnavbartests.Util.FirestoreUtil
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.edittextPersonname
import kotlinx.android.synthetic.main.fragment_home.submitbutton
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeFragment : Fragment() {

    private val RC_SELECT_IMAGE = 2
    private lateinit var selectedImageBytes: ByteArray
    private var pictureJustChanged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.apply {

            imageView_profile_picture.setOnClickListener {
                val intent = Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                    putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
                }
                startActivityForResult(Intent.createChooser(intent, "Select Image"), RC_SELECT_IMAGE)
            }


            submitbutton.setOnClickListener {
                FirestoreUtil.updateCurrentUser(
                    edittextPersonname.text.toString(),
                    editTextBio.text.toString(),
                    editTextTextEmailAddress.text.toString(),
                    edittextage.text.toString()

                    )
                Toast.makeText(this@HomeFragment.requireActivity(), "saving", Toast.LENGTH_SHORT).show()
            }

            return view
        }

    }


    override fun onStart() {
        super.onStart()
        FirestoreUtil.getCurrentUser { user ->
            if (this@HomeFragment.isVisible){
                edittextPersonname.setText(user.name)
                editTextBio.setText(user.bio)
                editTextTextEmailAddress.setText(user.email)
                edittextage.setText(user.age)

            }
        }
    }
}