package com.kang.administrator.myankosqliteapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var keys=listOf("key1","key2","key3")
        var values=listOf("value0","calue1","value2")

        build_sqlite.setOnClickListener{
            DBUtils(applicationContext,DatabaseHelper.InfoTbale.TABLE_NAME).updateTable(keys,values)
        }

        inserts.setOnClickListener {

            DBUtils(applicationContext,DatabaseHelper.InfoTbale.TABLE_NAME).insertInfo(key.text.toString(),value.text.toString())

        }

        updates.setOnClickListener {
            DBUtils(applicationContext,DatabaseHelper.InfoTbale.TABLE_NAME).insertInfo(key.text.toString(),value.text.toString())
        }

        selects.setOnClickListener {
            resultText.text=DBUtils(applicationContext,DatabaseHelper.InfoTbale.TABLE_NAME).selectInfo("key1")
        }

    }
}
