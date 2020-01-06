package com.cristopher.mauratzjarl.ui.auth


import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
import com.cristopher.mauratzjarl.databinding.ActivityLoginBinding
import com.cristopher.mauratzjarl.ui.homeActivity.MainNavigationActivity
import com.pspdfkit.labs.vangogh.api.AnimationSchedulers.together
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeIn
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeOut
import com.pspdfkit.labs.vangogh.api.ScaleAnimations.scaleTo
import com.pspdfkit.labs.vangogh.api.TranslateAnimations.translateTo
import com.suke.widget.SwitchButton
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity  : AppCompatActivity(), AuthListener,KodeinAware{



    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private var rootView:ViewGroup? = null
    private var headView:ViewGroup? = null
    private var loginView:ViewGroup? = null
    private var btnView:Button? = null
    private var logoView:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.AuthListener = this


        rootView = findViewById(R.id.root_layout) as ViewGroup
        headView = findViewById(R.id.bg_view) as ViewGroup
        loginView = findViewById(R.id.login_view) as ViewGroup
        btnView = findViewById(R.id.button_skip) as Button
        logoView = findViewById(R.id.imageView) as ImageView

        val switch = findViewById(R.id.switch_button) as SwitchButton

        val emailView = findViewById(R.id.editText_email) as EditText

        val passwordView = findViewById(R.id.editText_pass) as EditText


        fadeOut(rootView!!,0)
            .andThen(fadeOut(headView!!,0))
            .andThen(scaleTo(headView!!,0F,0))
            .andThen(fadeOut(loginView!!,0))
            .andThen(translateTo(loginView!!,0F,1000F,0))
            .andThen(
                together(
                  fadeIn(rootView!!,0),fadeIn(headView!!,500),scaleTo(headView!!,1F,500),translateTo(loginView!!,0F,0F,500),fadeIn(loginView!!,500)
            )
            )
            .subscribe()

        scaleTo(passwordView,0F)
            .andThen(together(
                scaleTo(passwordView,0F),translateTo(loginView!!,0F,0F)
            ))
            .subscribe()




        emailView.setOnFocusChangeListener { view, b ->

            passwordView.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_password_light, 0,R.drawable.icon_eye_light , 0)
            emailView.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_email_dark, 0, 0, 0)

        }


        passwordView.setOnFocusChangeListener { view, b ->

            emailView.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_email_light, 0, 0, 0)
            passwordView.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_password_dark, 0, R.drawable.icon_eye_dark, 0)

        }

        switch.setOnCheckedChangeListener { view, isChecked ->

            if(isChecked){
                scaleTo(passwordView,0F)
                    .andThen(together(
                        scaleTo(passwordView,1F),translateTo(switch,0F,200F)
                    ))
                    //.andThen(scaleTo(passwordView,1F))
                    .subscribe()

                //passwordView.visibility  = View.VISIBLE
            }else{
                scaleTo(passwordView,1F)
                    .andThen(together(
                        scaleTo(passwordView,0F),translateTo(switch,0F,0F)
                    ))
                    .subscribe()
            }

        }
        KeyboardEventListener(this) {

            if (it) {
                fadeIn(headView!!,0)
                    .andThen(together(
                        translateTo(headView!!,0F,-400F,500),translateTo(loginView!!,0F,-500F,500),translateTo(btnView!!,0F,500F,500),translateTo(logoView!!,0F,210F,500)/*,scaleTo(logoView!!,1F)*/
                    ))
                    .subscribe()

            } else {
                fadeIn(headView!!,0)
                    .andThen(together(
                        translateTo(loginView!!,0F,0F,500),translateTo(headView!!,0F,0F,500),translateTo(btnView!!,0F,0F,500),translateTo(logoView!!,0F,0F,500)//*,scaleTo(logoView!!,0F)*//*
                    ))
                    .subscribe()
            }
        }


    }

    override fun onPause() {
        super.onPause()
        translateTo(headView!!,0F,0F,0)
            .andThen(together(
                translateTo(loginView!!,0F,0F,0),translateTo(btnView!!,0F,0F,0),translateTo(logoView!!,0F,0F,0)//*,scaleTo(logoView!!,0F)*//*
            ))
            .subscribe()
    }



    override fun onBackPressed() {
        super.onBackPressed()


    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSucess() {
        progress_bar.hide()
        val intent = Intent(this, MainNavigationActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
        finish()
    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }

    override fun onSucessSignUp() {

    }

}
