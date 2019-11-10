package com.cristopher.mauratzjarl.ui.auth

import com.cristopher.mauratzjarl.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSucess(user: User)
    fun onFailed(message:String)

    fun onSucessSignUp()
}