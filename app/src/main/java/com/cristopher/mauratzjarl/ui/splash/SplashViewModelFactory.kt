package com.cristopher.mauratzjarl.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cristopher.mauratzjarl.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>):T {
        return SplashViewModel(repository) as T
    }
}
