package com.cristopher.mauratzjarl.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CUREENT_LIST_ID = 0

@Entity
data class  listResponceDB (

    val id: Int,
    val image: String,
    val is_new: Int,
    val language: String,
    val like_percent: Int,
    val rating: String,
    val title: String,
    val type: String,
    val vote_count: Int

){
    @PrimaryKey(autoGenerate = false)
    var uid :Int = CUREENT_LIST_ID
}