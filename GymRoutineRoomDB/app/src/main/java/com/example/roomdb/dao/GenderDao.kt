package com.example.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdb.model.Gender

@Dao
interface GenderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGender(gender: Gender)

    @Update
    fun updateGender(gender: Gender)

    @Delete
    fun deleteGender(gender: Gender)

    @Query("SELECT * FROM Gender WHERE name == :name")
    fun getGenderByName(name: String): List<Gender>

    @Query("SELECT * FROM Gender")
    fun getGenders(): List<Gender>

}