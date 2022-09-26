package com.example.movieapp.data.local

import androidx.room.TypeConverter
import com.example.movieapp.data.remote.dto.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MyConverters {
    // Object
    @TypeConverter
    fun fromObjectToSerial(objects: Object?): String? {
        val gson = Gson()
        return if (objects == null) null else gson.toJson(objects)
    }

    @TypeConverter
    fun fromSerialToObject(serialObj: String?): Object? {
        return if (serialObj == null) null
        else Gson().fromJson(serialObj, Object::class.java)
    }

    // List<String?>?
    @TypeConverter
    fun fromListStrToString(list: List<String?>?): String? {
        val gson = Gson()
        return if (list == null) null else gson.toJson(list)
    }
    @TypeConverter
    fun fromStringToLisStr(serialListStr: String?): List<String?>? {
        return if (serialListStr == null) null
        else {
            val listType: Type = object : TypeToken<List<String?>?>() {}.type
            val yourClassList: List<String> = Gson().fromJson(serialListStr, listType)
            yourClassList
        }
    }

    // List<ExtraText?>?
    @TypeConverter
    fun fromListEtToString(list: List<ExtraText?>?): String? {
        val gson = Gson()
        return if (list == null) null else gson.toJson(list)
    }
    @TypeConverter
    fun fromStringToListEt(serialListStr: String?): List<ExtraText?>? {
        return if (serialListStr == null) null
        else {
            val listType: Type = object : TypeToken<List<ExtraText?>?>() {}.type
            val yourClassList: List<ExtraText> = Gson().fromJson(serialListStr, listType)
            yourClassList
        }
    }

    // ExtraConfig?
    @TypeConverter
    fun fromExtraConfigToString(extraConfig: ExtraConfig?): String? {
        val gson = Gson()
        return if (extraConfig == null) null else gson.toJson(extraConfig)
    }
    @TypeConverter
    fun fromStringToExtraConfig(serialExtraConfig: String?): ExtraConfig? {
        return if (serialExtraConfig == null) null
        else Gson().fromJson(serialExtraConfig, ExtraConfig::class.java)
    }

    // LicenseRequestHeaders?
    @TypeConverter
    fun fromLRHToString(licenseReqHead: LicenseRequestHeaders?): String? {
        val gson = Gson()
        return if (licenseReqHead == null) null else gson.toJson(licenseReqHead)
    }
    @TypeConverter
    fun fromStringToLRH(serialLicenseReqHead: String?): LicenseRequestHeaders? {
        return if (serialLicenseReqHead == null) null
        else Gson().fromJson(serialLicenseReqHead, LicenseRequestHeaders::class.java)
    }

    // LicenseServers?
    @TypeConverter
    fun fromLSToString(licenseServers: LicenseServers?): String? {
        val gson = Gson()
        return if (licenseServers == null) null else gson.toJson(licenseServers)
    }
    @TypeConverter
    fun fromStringToLS(serialLicenseServers: String?): LicenseServers? {
        return if (serialLicenseServers == null) null
        else Gson().fromJson(serialLicenseServers, LicenseServers::class.java)
    }

    // StoredContent?
    @TypeConverter
    fun fromSCToString(storedContent: StoredContent?): String? {
        val gson = Gson()
        return if (storedContent == null) null else gson.toJson(storedContent)
    }
    @TypeConverter
    fun fromStringToSC(serialSContent: String?): StoredContent? {
        return if (serialSContent == null) null
        else Gson().fromJson(serialSContent, StoredContent::class.java)
    }
}