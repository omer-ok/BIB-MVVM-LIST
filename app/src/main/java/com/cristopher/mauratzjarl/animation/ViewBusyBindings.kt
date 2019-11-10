package com.cristopher.mauratzjarl.animation

import android.view.View
import android.view.animation.TranslateAnimation
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.databinding.BindingAdapter
import androidx.interpolator.view.animation.FastOutSlowInInterpolator



class ViewBusyBindings {

    private val INTERPOLATOR = FastOutSlowInInterpolator()

    @BindingAdapter("isBusy")
    fun setIsBusy(view: View, isBusy: Boolean) {
        val animation = view.getAnimation()
        if (isBusy && animation == null) {
            view.startAnimation(createAnimation())
        } else if (animation != null) {
            animation!!.cancel()
            view.setAnimation(null)
        }
    }

    private fun createAnimation(): Animation {
        val anim = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.interpolator = INTERPOLATOR
        anim.duration = 1400
        anim.repeatCount = TranslateAnimation.INFINITE
        anim.repeatMode = TranslateAnimation.RESTART
        return anim

    }

}