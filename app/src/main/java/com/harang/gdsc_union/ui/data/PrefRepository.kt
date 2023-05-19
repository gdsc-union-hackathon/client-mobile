package com.harang.gdsc_union.ui.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.harang.gdsc_union.GdscUnionApplication

object SharedPreferencesManager {
    private val pref: SharedPreferences = GdscUnionApplication.applicationContext().getSharedPreferences("GdscUnionSharedPreferences", Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

    private fun putStringAndCommit(key: String, string: String) {
        editor.putString(key, string)
        editor.commit()
    }

    private fun getStringAndReturn(key: String): String {
        return pref.getString(key, "") ?: ""
    }

    fun putString(key: String, value: String) {
        putStringAndCommit(key, value)
    }

    fun getString(key: String): String {
        return getStringAndReturn(key)
    }
}