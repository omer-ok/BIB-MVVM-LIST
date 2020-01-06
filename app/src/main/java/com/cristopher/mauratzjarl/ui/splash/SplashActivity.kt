package com.cristopher.mauratzjarl.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cristopher.mauratzjarl.R
import com.cristopher.mauratzjarl.ui.homeActivity.MainNavigationActivity
import com.cristopher.mauratzjarl.ui.auth.LoginActivity
import com.pspdfkit.labs.vangogh.api.AnimationSchedulers.together
import com.pspdfkit.labs.vangogh.api.FadeAnimations
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeIn
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeOut
import com.pspdfkit.labs.vangogh.api.ScaleAnimations
import com.pspdfkit.labs.vangogh.api.ScaleAnimations.scaleTo
import com.pspdfkit.labs.vangogh.api.TranslateAnimations
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SplashActivity : AppCompatActivity() , KodeinAware {

    override val kodein by kodein()
    private val factory : SplashViewModelFactory by instance()
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)




        viewModel = ViewModelProviders.of(this,factory).get(SplashViewModel::class.java)

        val logoView = findViewById(R.id.logo_view) as ViewGroup
        val rootView = findViewById(R.id.root_layout) as ViewGroup
        val View = findViewById(R.id.view) as View
        val imageView = findViewById(R.id.imageView) as ImageView

        fadeOut(rootView,0)
            .andThen(scaleTo(imageView, 0F))
            .andThen(
                together(
                    fadeIn(rootView),scaleTo(imageView, 1F,500)
                )
            )
            .subscribe()



        after(1500, {
            viewModel.getLoggedInUser().observe(this, Observer { user ->
                if (user != null) {
                    val intent = Intent(this, MainNavigationActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish()
                }
            })
        })
    }

    fun after(delay: Long, process: () -> Unit) {
        Handler().postDelayed({
            process()
        }, delay)
    }
}
