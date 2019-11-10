package com.cristopher.mauratzjarl.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.cristopher.mauratzjarl.R
import com.cristopher.mauratzjarl.ui.homeActivity.MainNavigationActivity
import com.pspdfkit.labs.vangogh.api.AnimationSchedulers.together
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeIn
import com.pspdfkit.labs.vangogh.api.FadeAnimations.fadeOut
import com.pspdfkit.labs.vangogh.base.AnimationBuilder
import com.pspdfkit.labs.vangogh.rx.AnimationCompletable

class LoginEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)

        val manuallyEnterOtp = findViewById(R.id.maually_enter_otp) as TextView
        val viewDes = findViewById(R.id.view) as ViewGroup
        val viewHead = findViewById(R.id.headerLyout) as ViewGroup

        fadeIn(viewHead)
            .andThen(fadeIn(viewDes))
            .subscribe();

        manuallyEnterOtp.setOnClickListener {

            val intent = Intent(this, OTPVerificationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)

        }
    }

}
