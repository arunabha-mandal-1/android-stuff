package com.example.roomdb.converter

import androidx.room.TypeConverter
import com.example.roomdb.model.Exercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections

class ListConverter {
    private val gson = Gson()

    @TypeConverter
    fun someStringToObjectList(data: String?): List<Exercise>? {
        if(data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Exercise>?): String? {
        return gson.toJson(someObjects)
    }

}