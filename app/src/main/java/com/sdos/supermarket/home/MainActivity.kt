package com.sdos.supermarket.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.sdos.supermarket.R
import com.sdos.supermarket.common.BaseActivity
import com.sdos.supermarket.login.LoginFragmentDirections
import com.sdos.supermarket.manager.ManagerFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_header.*


class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
    }

    private fun setUpNavigation() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment? ?: return
        val navController = host.navController
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        val sideNavView = findViewById<NavigationView>(R.id.navView)

        val logout = sideNavView.getHeaderView(0).findViewById<TextView>(R.id.logoutTV)
        logout.setOnClickListener {
            findNavController(R.id.navHostFragment).navigateUp()
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.manager_dest, R.id.technician_dest),
            drawerLayout
        )

        manageDestinations(navController, drawerLayout, sideNavView)
    }

    private fun manageDestinations(
        navController: NavController,
        drawerLayout: DrawerLayout?,
        sideNavView: NavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splash_dest -> {
                    toolbar.visibility = View.GONE
                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                R.id.manager_dest, R.id.technician_dest -> {
                    toolbar.visibility = View.VISIBLE
                    supportActionBar?.title = destination.label

                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    setupActionBarWithNavController(navController, appBarConfiguration)
                    sideNavView.setupWithNavController(navController)
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                    supportActionBar?.title = destination.label
                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
            }
        }
    }
}