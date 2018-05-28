package com.kang.administrator.myankosqliteapplication

import android.content.Context
import android.util.Log
import org.jetbrains.anko.db.*

class DBUtils(ctx:Context,var table:String) {
    var database:DatabaseHelper
    init {
        database=DatabaseHelper(ctx)
    }

    fun selectInfo(key:String): String? {
        var value: String? =null
        database.use {
            value=select(table, DatabaseHelper.InfoTbale.VALUE).whereSimple("${DatabaseHelper.InfoTbale.KEY} = \"${key}\"").exec { parseList(classParser<String>()) }.get(0)
        }
        return value
    }

    fun insertInfo(key:String,value:String){
        database.use {
            insert(table, DatabaseHelper.InfoTbale.KEY to key, DatabaseHelper.InfoTbale.VALUE to value)
        }
    }

    fun updateInfo(key:String,value:String){
        database.use {
            update(table,DatabaseHelper.InfoTbale.VALUE to value).whereSimple("key=\"${key}\"").exec()
        }

    }

    fun updateTable(keys:List<String>,values:List<String>){
        var keyhad:List<String>?=null
        database.use {
            keyhad=select(table, DatabaseHelper.InfoTbale.KEY).exec { parseList(classParser<String>()) }
            var keyhadv=select(table, DatabaseHelper.InfoTbale.VALUE).exec { parseList(classParser<String>()) }
        }
        var isFirst=(keyhad==null)
        for (i in keys.indices){
            if((!isFirst)&&keys[i] in keyhad!!){
                updateInfo(keys[i],values[i])
            }else{
                insertInfo(keys[i],values[i])
            }
        }

    }

}