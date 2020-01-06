package com.cristopher.mauratzjarl.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cristopher.mauratzjarl.R
import com.cristopher.mauratzjarl.Utilz.KeyboardEventListener
import com.cristopher.mauratzjarl.Utilz.hide
import com.cristopher.mauratzjarl.Utilz.show
import com.cristopher.mauratzjarl.Utilz.snackbar
import com.cristopher.mauratzjarl.data.db.entities.User
import com.cristopher.mauratzjarl.databinding.ActivitySignupBinding
import com.cristopher.mauratzjarl.ui.homeActivity.MainNavigationActivity
import com.pspdfkit.labs.vangogh.api.AnimationSchedulers.together
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeIn
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeOut
import com.pspdfkit.labs.vangogh.api.ScaleAnimations.scaleTo
import com.pspdfkit.labs.vangogh.api.TranslateAnimations.translateTo
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {



    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()


    private var rootView: ViewGroup? = null
    private var headView: ViewGroup? = null
    private var signUpView: ViewGroup? = null
    private var logoView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivitySignupBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.AuthListener = this

        rootView = findViewById(R.id.root_layout) as ViewGroup
        headView = findViewById(R.id.bg_view) as ViewGroup
        signUpView = findViewById(R.id.signup_view) as ViewGroup
        logoView = findViewById(R.id.imageView) as ImageView

        /*viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this, MainNavigationActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })*/

        fadeOut(rootView!!, 0)
            .andThen(fadeOut(headView!!, 0))
            .andThen(scaleTo(headView!!, 0F, 0))
            .andThen(fadeOut(signUpView!!, 0))
            .andThen(translateTo(signUpView!!, 0F, 1000F, 0))
            .andThen(
                together(
                    fadeIn(rootView!!, 0),
                    fadeIn(headView!!,500),
                    scaleTo(headView!!, 1F,500),
                    translateTo(signUpView!!, 0F, 0F, 500),
                    fadeIn(signUpView!!,500)
                )
            )
            .subscribe()

        KeyboardEventListener(this) {

            if (it) {
                fadeIn(headView!!,0)
                    .andThen(together(
                        translateTo(headView!!,0F,-400F,500),translateTo(signUpView!!,0F,-500F,500),translateTo(logoView!!,0F,210F,500)/*,scaleTo(logoView!!,1F)*/
                    ))
                    .subscribe()

            } else {
                fadeIn(headView!!,0)
                    .andThen(together(
                        translateTo(signUpView!!,0F,0F,500),translateTo(headView!!,0F,0F,500),translateTo(logoView!!,0F,0F,500)//*,scaleTo(logoView!!,0F)*//*
                    ))
                    .subscribe()
            }
        }



    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSucess() {
        progress_bar.hide()
        val intent = Intent(this, MainNavigationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }


    override fun onSucessSignUp() {
        progress_bar.hide()
        val intent = Intent(this, LoginEmailActivity::class.java)
        startActivity(intent)
        finish()
    }

}
