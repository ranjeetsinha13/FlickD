package com.rs.flickd.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rs.flickd.R
import com.rs.flickd.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ViewDataBinding


    protected abstract fun getLayout(): Int

    protected abstract fun getActivityTitle(): Int

    protected fun getBinding(): ViewDataBinding {
        return binding

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        initNavView()
        setTitle(getActivityTitle())

        setHomeFragment()


    }

    private fun setHomeFragment() {
        var fragmentByTag = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.name)
        if (fragmentByTag == null)
            fragmentByTag = HomeFragment()
        setFragment(fragmentByTag)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val fragments = supportFragmentManager.backStackEntryCount
            Timber.d("fragment count %s", fragments)
            if (fragments <= 1) {
                finish()
            }

        }
        super.onBackPressed()
    }

    private fun initNavView() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener({
            when (it.itemId) {

                R.id.nav_home -> {
                    setHomeFragment()

                }
                R.id.nav_profile -> {

                }
                R.id.nav_about -> {

                }
                R.id.nav_share -> {

                }

            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
        )
        bottom_nav_view.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_search -> {


                }
                R.id.nav_favorites -> {

                }
                R.id.nav_home -> {
                    setHomeFragment()

                }
            }

            true
        })
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentName = fragment::class.java.name
        val fragmentPopped = supportFragmentManager.popBackStackImmediate(fragmentName, 0)
        if (!fragmentPopped && supportFragmentManager.findFragmentByTag(fragmentName) == null) {
            supportFragmentManager.beginTransaction().replace(R.id.main_content, fragment,
                    fragmentName).addToBackStack(fragmentName).commit()
        }

    }
}