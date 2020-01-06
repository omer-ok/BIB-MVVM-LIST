package com.cristopher.mauratzjarl.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.cristopher.mauratzjarl.Utilz.ApiException
import com.cristopher.mauratzjarl.Utilz.Coroutines
import com.cristopher.mauratzjarl.Utilz.NoInternetException
import com.cristopher.mauratzjarl.Utilz.genericCastOrNull
import com.cristopher.mauratzjarl.data.db.entities.User
import com.cristopher.mauratzjarl.data.repositories.UserRepository
import com.cristopher.mauratzjarl.ui.homeActivity.MainNavigationActivity



class  AuthViewModel(
    private val repository: UserRepository
) : ViewModel(){

    var name : String? = null
    var email :String?=null
    var password :String?=null

    var AuthListener : AuthListener?=null
    fun getLoggedInUser() = repository.getUser()

    fun LoginOnClick(view:View){

        if(!email.isNullOrEmpty() && password.isNullOrEmpty()){
            Intent(view.context, LoginEmailActivity::class.java).also {
                it.putExtra("email", email)
                view.context.startActivity(it)
            }
        }else{
            AuthListener?.onStarted()
            if(email.isNullOrEmpty() || password.isNullOrEmpty()){
                AuthListener?.onFailed("Invalid Password")
                return
            }

            AuthListener?.onSucess()
            /*Coroutines.main {
                try {
                    val authResponse = repository.userLogin(email!!,password!!)
                    authResponse.result?.let {
                        //AuthListener?.onSucess(genericCastOrNull<User>(it))
                        genericCastOrNull<User>(it)?.let { it1 -> repository.saveUser(it1) }
                        return@main
                    }
                    AuthListener?.onFailed(authResponse.message!!)
                }catch (e : ApiException){AuthListener?.onFailed(e.message!!)
                }catch (e : NoInternetException){AuthListener?.onFailed(e.message!!)}
            }*/
        }
    }


    fun onSkipClick(view : View) {
        Intent(view.context, MainNavigationActivity::class.java).also {
            view.context.startActivity(it)
        }

    }

    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onSignupButtonClick(view: View){
        AuthListener?.onStarted()

        if(name.isNullOrEmpty()){
            AuthListener?.onFailed("Name is required")
            return
        }

        if(email.isNullOrEmpty()){
            AuthListener?.onFailed("Email is required")
            return
        }

        if(password.isNullOrEmpty()){
            AuthListener?.onFailed("Please enter a password")
            return
        }

/*        if(password != passwordconfirm){
            AuthListener?.onFailed("Password did not match")
            return
        }*/

        AuthListener?.onSucessSignUp()
       /* Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.result?.user?.let {
                    AuthListener?.onSucess(it)
                    repository.saveUser(it)
                    return@main
                }
                AuthListener?.onFailed(authResponse.message!!)
            }catch(e: ApiException){
                AuthListener?.onFailed(e.message!!)
            }catch (e: NoInternetException){
                AuthListener?.onFailed(e.message!!)
            }
        }*/

    }





}