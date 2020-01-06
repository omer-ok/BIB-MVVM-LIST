package com.cristopher.mauratzjarl.data.network


import com.cristopher.mauratzjarl.data.db.entities.User
import com.cristopher.mauratzjarl.data.db.entities.listResponceDB
import com.cristopher.mauratzjarl.data.network.responces.AuthResponce
import com.cristopher.mauratzjarl.data.network.responces.NetworkConnectionInterceptor
import com.cristopher.mauratzjarl.data.network.responces.ResultResponce
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.model.Movie
import okhttp3.OkHttpClient

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("userlogin")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password : String
    ) : Response<AuthResponce<User?>>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponce<User?>>


    @GET("movies")
    suspend fun getListData() : Response<List<Movie>>

    companion object{
        operator fun invoke(
            interceptor: NetworkConnectionInterceptor
        ) :Api{

            val okkHttpClient =OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/recyclerview/")
                //.baseUrl("http://api.rikskampen.se/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }


}