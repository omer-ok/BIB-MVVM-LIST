package com.cristopher.mauratzjarl.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun updateUserLocalDb(user: User):Long

      @Query("SELECT * FROM user WHERE uid =$CUREENT_USER_ID")
      fun getUser() : LiveData<User>

      @Query("DELETE FROM user")
      abstract fun logoutUser()
}