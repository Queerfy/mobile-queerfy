package com.example.queerfy.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.queerfy.R
import com.example.queerfy.databinding.ActivityNavigationDrawerBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding

    val arrayList = listOf("São Paulo", "Rio de Janeiro")
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setSupportActionBar(this.binding.appBarNavigationDrawer.toolbar)

        this.binding.appBarNavigationDrawer.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = this.binding.drawerLayout
        val navView: NavigationView = this.binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        this.arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
//        this.binding.listView.adapter = this.arrayAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation_drawer, menu)

        val search = menu.findItem(R.id.action_search)
        val editSearch = search.actionView as SearchView
        search.collapseActionView()
        editSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Teste: ", "Escolheu")
                search.collapseActionView()
                search.expandActionView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Teste: ", "Digitando...")
                search.expandActionView()
                arrayAdapter.filter.filter(newText)
                return false
            }

        })

            return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}