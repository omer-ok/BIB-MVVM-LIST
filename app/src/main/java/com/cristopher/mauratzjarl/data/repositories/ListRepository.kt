package com.cristopher.mauratzjarl.data.repositories

import androidx.lifecycle.LiveData
import com.cristopher.mauratzjarl.data.db.entities.AppDatabase
import com.cristopher.mauratzjarl.data.db.entities.listResponceDB
import com.cristopher.mauratzjarl.data.network.Api
import com.cristopher.mauratzjarl.data.network.responces.SafeApiRequest
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.model.Movie

class ListRepository (
    private val api : Api,
    private val db : AppDatabase
) : SafeApiRequest(){


    suspend fun ListData():List<Movie>{
        return apiRequest{api.getListData()}
    }

}