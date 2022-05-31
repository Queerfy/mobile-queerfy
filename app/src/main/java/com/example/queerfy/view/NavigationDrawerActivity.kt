package com.example.queerfy.view

import android.content.ClipData
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
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
import java.text.Normalizer


class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding
    private lateinit var userPreferences: SharedPreferences

    private val arrayList = listOf("São Paulo", "Rio de Janeiro")
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        userPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE)

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

        navView.setNavigationItemSelectedListener { menuItem ->

            val idUser = userPreferences.getInt("idUser", 0)

            val loginPage = Intent(this, LoginFormActivity::class.java)

            when (menuItem.itemId) {

                R.id.nav_home -> {
                    val homepage = Intent(this, NavigationDrawerActivity::class.java)

                    startActivity(homepage)
                }

                R.id.myAds -> {

                    if (idUser == 0) {
                        startActivity(loginPage)
                    } else {
                        val adsPage = Intent(this, MyAdsActivity::class.java)

                        startActivity(adsPage)
                    }

                }

                R.id.myAccount -> {

                    if (idUser == 0) {
                        startActivity(loginPage)
                    } else {
                        val accountPage = Intent(this, AccountActivity::class.java)

                        startActivity(accountPage)
                    }
                }

                R.id.myFavorites -> {

                    if (idUser == 0) {
                        startActivity(loginPage)
                    } else {
                        val myFavoritePage = Intent(this, MyFavoritesActivity::class.java)

                        startActivity(myFavoritePage)
                    }

                }

                R.id.myReservations -> {

                    if (idUser == 0) {
                        startActivity(loginPage)
                    } else {
                        val myReservationsPage = Intent(this, MyReservationsActivity::class.java)

                        startActivity(myReservationsPage)
                    }
                }

                R.id.logout_item -> {
                    if (idUser !== 0) {
                        userPreferences.edit().remove("idUser").commit()

                        Toast(this).showCustomToast("Usuario deslogado com sucesso!", this)
                    }

                }
            }
            false
        }

        this.arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
//        this.binding.listView.adapter = this.arrayAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation_drawer, menu)

        val search = menu.findItem(R.id.action_search)
        val editSearch = search.actionView as SearchView
        search.collapseActionView()

        val listResidencePage = Intent(this, ResidenceListActivity::class.java)

        editSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Log.d("Teste: ", "Escolheu")
                search.collapseActionView()
                search.expandActionView()

                var queryFormated = Normalizer.normalize(query, Normalizer.Form.NFD);

                queryFormated =
                    Regex("\\p{InCombiningDiacriticalMarks}+").replace(queryFormated, "")
                        .lowercase().replace(" ", "-")

                listResidencePage.putExtra("city", queryFormated)
                listResidencePage.putExtra("cityNotFormated", query)

                startActivity(listResidencePage)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Log.d("Teste: ", "Digitando...")
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