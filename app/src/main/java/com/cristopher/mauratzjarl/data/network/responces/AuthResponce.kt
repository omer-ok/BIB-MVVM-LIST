package com.cristopher.mauratzjarl.data.network.responces



data class AuthResponce<T>(


    val status : String ?,
    val message : String ?,
    val code : String ?,
    val result : Any?
)