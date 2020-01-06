package com.cristopher.mauratzjarl.Utilz

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}

fun View.snackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .into(view)
}
