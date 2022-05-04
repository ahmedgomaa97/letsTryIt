package com.newcore.letstryit.model.local.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*
table user
    id
    name
    email
    phoneNumber
*/

class MySqlite constructor(
    context: Context,
) : SQLiteOpenHelper(context, "MyDatabase", null, 1) {

    companion object{
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PHONE_NUMBER = "phone_number"
        const val USERS_TABLE = "USERS"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("""
            CREATE TABLE $USERS_TABLE(
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME TEXT NOT NULL,
                $EMAIL TEXT NOT NULL,
                $PHONE_NUMBER TEXT NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        onCreate(p0)
    }




}