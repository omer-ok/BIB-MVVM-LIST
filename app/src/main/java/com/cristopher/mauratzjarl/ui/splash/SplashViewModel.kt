package com.cristopher.mauratzjarl.ui.splash

import androidx.lifecycle.ViewModel
import com.cristopher.mauratzjarl.data.repositories.UserRepository

class SplashViewModel(
    private val repository: UserRepository
) :ViewModel() {


    var email :String?=null



    fun getLoggedInUser() = repository.getUser()
}