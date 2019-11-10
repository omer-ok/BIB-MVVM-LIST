package com.cristopher.mauratzjarl.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CUREENT_USER_ID = 0

@Entity
data class  User (
    var token:String? =null,
    var id:Int? = null,
    var firstname:String? =null,
    var lastname:String? =null,
    var email:String? =null,
    var gender:String? =null,
    var dateOfBirth:String? =null,
    var streetAddress:String? =null,
    var phonenumber:String? =null,
    var profileImage:String? =null,
    var thumbImage:String? =null,
    var height:String? =null,
    var weight:String? =null,
    var roleid:Int? =null,
    var nationalid:Int? =null,
    var stepCount:Int? =null,
    var starCount:Int? =null,
    var location:String? =null,
    var ActivityTime:String? =null,
    var ActivityWeight:Int? =null,
    var ActivityImage:String? =null,
    var distanceSum:String? =null,
    var calorieUnit:String? =null,
    var distanceUnit:String? =null,
    var alreadyLogin:Boolean? =null,
    var waist:Int? =null,
    var caloriesCount:String? =null,
    var trainerName:String? =null,
    var userStatus:String? =null,
    var trainerNumber:String? =null,
    var trainerPhoto:String? =null,
    var trainerId:Int? =null,
    var paymentStatus:String? =null,
    var paymentMethod:String? =null,
    var paymentDate:String? =null,
    var starRank:String? =null,
    var stepsRank:String? =null,
    var caloriesRank:String? =null,
    var contesStarDate:String? =null,
    var contesEndDate:String? =null

){
    @PrimaryKey(autoGenerate = false)
    var uid :Int = CUREENT_USER_ID
}