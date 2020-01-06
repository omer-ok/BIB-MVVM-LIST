package com.cristopher.mauratzjarl.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
@Dao
interface ListDao {

    @Query("SELECT * FROM listResponceDB WHERE uid =$CUREENT_LIST_ID")
    fun getListData() : LiveData<listResponceDB>
}