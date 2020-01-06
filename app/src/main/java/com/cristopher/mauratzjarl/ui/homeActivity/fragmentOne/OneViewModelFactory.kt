package com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cristopher.mauratzjarl.data.repositories.ListRepository

class OneViewModelFactory (
    private val repository: ListRepository
    ): ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>):T {
            return OneViewModel(repository) as T
        }
    }

