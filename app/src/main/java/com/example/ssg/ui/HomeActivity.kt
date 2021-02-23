package com.example.ssg.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ssg.Fragment.AreneFragment
import com.example.ssg.Fragment.FriendFragment
import com.example.ssg.Fragment.HomeFragment
import com.example.ssg.Fragment.UserFragment
import com.example.ssg.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity:AppCompatActivity() {
    var homeFragment = HomeFragment()
    var friendFrag =FriendFragment()
    var areneFragment = AreneFragment()
    var userFragment = UserFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        changeFragment(homeFragment,"home")
        initView()

    }

    private fun initView() {
        bottomNav.setOnItemSelectedListener(object :ChipNavigationBar.OnItemSelectedListener{
            override fun onItemSelected(id: Int) {
                when(id){
                    R.id.home11->{
                        changeFragment(homeFragment,"home")
                    }
                    R.id.friend->{
                        changeFragment(friendFrag,"friend")
                    }
                    R.id.bazar->{

                    }
                    R.id.arene->{
                        changeFragment(areneFragment,"arene")
                    }
                    R.id.user->{
                        changeFragment(userFragment,"user")
                    }
                }
            }

        })
    }

    fun changeFragment(fragmet: Fragment,backstack:String){
        framelayout.removeAllViews()
        val fragmentManagar:FragmentManager=supportFragmentManager
        fragmentManagar
                .beginTransaction()
                .replace(R.id.framelayout,fragmet)
                .addToBackStack(backstack)
                .commit()
    }


}
