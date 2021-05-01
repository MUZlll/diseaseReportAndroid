package com.muz1i.diseasereportandroid.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.databinding.ActivityMainBinding
import com.muz1i.diseasereportandroid.utils.Constants

class MainActivity : AppCompatActivity(), ViewModelStoreOwner {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permission: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        initData()
        initEvent()
    }

    private fun initData() {
//        permission = intent.getStringExtra(Constants.PERMISSION_KEY)
        permission = Constants.PERMISSION_ADMIN
        println("permission is -> $permission")
        permission.run {
            val that = this
            binding.bottomNavigation.run {
                menu[1].isVisible = that != Constants.PERMISSION_USER
                menu[2].isVisible = that == Constants.PERMISSION_ADMIN
                menu[3].isVisible = that != Constants.PERMISSION_USER
                menu[4].isVisible = that == Constants.PERMISSION_USER
            }
        }
    }

    private fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        navHostFragment.retainInstance = true
    }

    private fun initEvent() {
        val navigationButton = binding.mainToolbar.getChildAt(0)
        navigationButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
