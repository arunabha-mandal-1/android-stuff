package com.example.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdb.model.Routine
import java.util.Date

@Dao
interface RoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoutine(routine: Routine)

    @Update
    fun updateRoutine(routine: Routine)

    @Delete
    fun deleteRoutine(routine: Routine)

    @Query("SELECT * FROM Routine WHERE dueDay >= :due")
    fun getRoutineByDueDate(due: Date): List<Routine>
}