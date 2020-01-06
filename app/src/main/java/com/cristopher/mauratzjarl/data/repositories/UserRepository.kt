package com.cristopher.mauratzjarl.data.repositories



import com.cristopher.mauratzjarl.data.db.entities.AppDatabase
import com.cristopher.mauratzjarl.data.db.entities.User
import com.cristopher.mauratzjarl.data.network.Api
import com.cristopher.mauratzjarl.data.network.responces.AuthResponce
import com.cristopher.mauratzjarl.data.network.responces.ResultResponce
import com.cristopher.mauratzjarl.data.network.responces.SafeApiRequest


class UserRepository(
    private val api : Api,
    private val db : AppDatabase
) : SafeApiRequest(){

  suspend fun userLogin(email:String ,password :String ): AuthResponce<User?> {

        return apiRequest{api.userLogin(email,password)}

    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponce<User?> {
        return apiRequest{ api.userSignup(name, email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().updateUserLocalDb(user)

    fun getUser() = db.getUserDao().getUser()

    fun logoutUser() = db.getUserDao().logoutUser()


    }

