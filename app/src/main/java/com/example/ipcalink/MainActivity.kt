package com.example.ipcalink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ipcalink.databinding.ActivityMainBinding
import com.example.ipcalink.fragments.MessagesFragment
import com.example.ipcalink.fragments.ProfileFragment
import com.example.ipcalink.fragments.ReminderFragment
import com.example.ipcalink.login.LoginActivity
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import android.R.id
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.example.ipcalink.calendar.CalendarFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val messagesFragment = MessagesFragment()
    private val calendarFragment = CalendarFragment()
    private val reminderFragment = ReminderFragment()
    private val profileFragment = ProfileFragment()
    private var currentFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(messagesFragment)

        //load user profile image
        //TO DO

        //remove top bar better
        supportActionBar?.hide()

        //Bottom app bar top left and top right corner radius
        /*val bottomAppBar = binding.bottomAppBar
        val bottomBarBackground = bottomAppBar.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, 60f)
            .setTopLeftCorner(CornerFamily.ROUNDED, 60f)
            .build() */

        /*binding.fabAdd.setOnClickListener {
            if(currentFragment == 1) {

            } else {

            }
        }*/

        binding.ibMessages.setOnClickListener {
            replaceFragment(messagesFragment)
            disableAllUnderlines()
            replaceAllBottomNavIcons()
            binding.mainBottomNavMessagesUnderline.visibility = View.VISIBLE
            binding.ibMessages.setImageResource(R.drawable.ic_selected_messages_icon)
            //verifyCurrentFragment(1)
        }

        binding.ibCalendar.setOnClickListener {
            replaceFragment(calendarFragment)
            disableAllUnderlines()
            replaceAllBottomNavIcons()
            binding.mainBottomNavCalendarUnderline.visibility = View.VISIBLE
            binding.ibCalendar.setImageResource(R.drawable.ic_selected_calendar_icon)
           //verifyCurrentFragment(2)
        }

        binding.ibReminder.setOnClickListener {
            replaceFragment(reminderFragment)
            disableAllUnderlines()
            replaceAllBottomNavIcons()
            binding.mainBottomNavReminderUnderline.visibility = View.VISIBLE
            binding.ibReminder.setImageResource(R.drawable.ic_selected_reminder_icon)
            //verifyCurrentFragment(3)
        }

        binding.ibProfileImage.setOnClickListener {
            replaceFragment(profileFragment)
            disableAllUnderlines()
            replaceAllBottomNavIcons()
            binding.mainBottomNavProfileUnderline.visibility = View.VISIBLE
            //verifyCurrentFragment(4)
        }
    }

    private fun replaceFragment(fragment: Fragment?) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainer, fragment)
            transaction.commit()

        }
    }

    private fun disableAllUnderlines() {
        binding.mainBottomNavMessagesUnderline.visibility = View.INVISIBLE
        binding.mainBottomNavCalendarUnderline.visibility = View.INVISIBLE
        binding.mainBottomNavReminderUnderline.visibility = View.INVISIBLE
        binding.mainBottomNavProfileUnderline.visibility = View.INVISIBLE
    }

    private fun enableAllUnderlines() {
        binding.mainBottomNavMessagesUnderline.visibility = View.VISIBLE
        binding.mainBottomNavCalendarUnderline.visibility = View.VISIBLE
        binding.mainBottomNavReminderUnderline.visibility = View.VISIBLE
        binding.mainBottomNavProfileUnderline.visibility = View.VISIBLE
    }

    private fun replaceAllBottomNavIcons() {
        binding.ibMessages.setImageResource(R.drawable.ic_unselected_messages_icon)
        binding.ibCalendar.setImageResource(R.drawable.ic_unselected_calendar_icon)
        binding.ibReminder.setImageResource(R.drawable.ic_unselected_reminder_icon)
    }

    fun signOut(view: View) {
        Firebase.auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    /*
    this function will verify which current fragment is displayed
    and hide the fbaAdd when on notification fragment and profile fragment

    fun verifyCurrentFragment(fragmentId: Int) {
        if(fragmentId == 3 || fragmentId == 4) {
            binding.fabAdd.visibility = View.GONE
        } else {
            binding.fabAdd.visibility = View.VISIBLE
            if(fragmentId == 1) {
                currentFragment = 1
            } else {
                currentFragment = 2
            }
        }
    }*/
}