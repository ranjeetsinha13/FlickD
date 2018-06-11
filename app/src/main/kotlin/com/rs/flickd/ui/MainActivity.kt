package com.rs.flickd.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.rs.flickd.App
import com.rs.flickd.R
import com.rs.flickd.databinding.ActivityMainBinding
import com.rs.flickd.viewModels.TmdbNowPlayingMoviesViewModel
import com.rs.flickd.viewModels.TmdbViewModel
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.longToast
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_main

    override fun getActivityTitle() = R.string.app_name

    companion object {
        val RC_SIGN_IN = 123
    }

    @Inject
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        loginUser()


    }

    private fun loginUser() {
        //Timber.d("user is not null${firebaseAuth.currentUser.toString()}")
        if (firebaseAuth.currentUser == null) {
            val providers = Arrays.asList(
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.FacebookBuilder().build())

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    MainActivity.RC_SIGN_IN)
        } else {
            // set the views; use databinding
            setUserInfo()
            //Timber.d("binding" +binding)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response: IdpResponse? = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK) {
                //handle success
                handleSignInSuccessful()

            } else {
                //handle error
                if (resultCode == Activity.RESULT_CANCELED) {
                    longToast(R.string.error_signup_cancelled)
                    finish()

                } else {
                    //response?.error?.errorCode
                    longToast(response?.error?.localizedMessage.toString())

                }

            }
        }

    }

    private fun handleSignInSuccessful() {
        setUserInfo()


    }

    private fun setUserInfo() {
        Timber.d(firebaseAuth.currentUser?.email)
        Timber.d(firebaseAuth.currentUser?.displayName)
        val binding = getBinding() as ActivityMainBinding
        binding.navView.getHeaderView(0).user_email?.text = firebaseAuth.currentUser?.email
        binding.navView.getHeaderView(0).user_name?.text = firebaseAuth.currentUser?.displayName


    }

}
