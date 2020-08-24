package com.example.bottomnavbartests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bottomnavbartests.Authentications.LogInActivity
import com.example.bottomnavbartests.Fragments.HomeFragment
import com.example.bottomnavbartests.Fragments.MyAccountFragment
import com.example.bottomnavbartests.Fragments.PeopleFragment
import com.example.bottomnavbartests.Fragments.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val homefragment = HomeFragment()
        val peoplefragment = PeopleFragment()
        val myaccountfragment =MyAccountFragment()
        val profilefragment =ProfileFragment()


        makeCurrentFragment( homefragment)

        navvbarr.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home ->makeCurrentFragment(homefragment)
                R.id.navigation_people ->makeCurrentFragment(peoplefragment)
                R.id.navigation_my_account ->makeCurrentFragment(myaccountfragment)
                R.id.navigation_profile ->makeCurrentFragment(profilefragment)
            }

            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.milogout ->
                 perfsignout()
        }
        return true
    }
    private fun perfsignout(){
        FirebaseAuth.getInstance().signOut()
        finish()
        startActivity(Intent(this, LogInActivity::class.java))

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_layout, fragment)
            commit()
        }

}