package com.example.movieapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*


class MyConverters {
    // Object
    @TypeConverter
    fun fromObjectToSerial(objects: Objects?): String? {
        val gson = Gson()
        return if (objects == null) null else gson.toJson(objects)
    }

    @TypeConverter
    fun fromSerialToAny(serialObj: String): Objects {
        return Gson().fromJson(serialObj, Objects::class.java)
    }

    // List<String?>?
    // ExtraConfig?
    // List<ExtraText?>?
    // LicenseRequestHeaders?
    // LicenseServers?
    // StoredContent?
}