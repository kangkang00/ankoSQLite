package com.kang.administrator.myankosqliteapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.SyncStateContract.Helpers.insert
import android.provider.Telephony
import org.jetbrains.anko.db.*
import org.jetbrains.anko.doAsync
import java.sql.Types

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "LibraryDatabase", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.createTable(InfoTbale.TABLE_NAME, true, InfoTbale.KEY to TEXT+ PRIMARY_KEY, InfoTbale.VALUE to TEXT)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (p0 != null) {
            p0.dropTable(InfoTbale.TABLE_NAME, true)
        }
    }

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun Instance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    val Context.database: DatabaseHelper
        get() = DatabaseHelper.Instance(applicationContext)


    data class InfoTbale(val key: String, val value: String) {
        companion object {
            val TABLE_NAME = "infoTbale"
            val KEY = "key"
            val VALUE = "value"
        }
    }
}