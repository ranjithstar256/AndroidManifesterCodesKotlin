package ran.am.androidmanifestercodeskotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Ranjith on 27-04-2017.
 */
class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Details.db", null, 1) {
    var sqLiteDatabase: SQLiteDatabase

    init {
        sqLiteDatabase = writableDatabase
    }

    override fun onCreate(db: SQLiteDatabase) {
        // query for creating table
        val qury = "create table Persons (Name text , Location text)"
        db.execSQL(qury)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    fun savedat(s1: String?, s2: String?): Long {
        val contentValues = ContentValues()
        contentValues.put("Name", s1)
        contentValues.put("Location", s2) // sqlite db
        return sqLiteDatabase.insert("Persons", null, contentValues)
    }

    fun getloc(name: String): String {
        val s: String
        val c = sqLiteDatabase.query("Persons", null, "Name=?", arrayOf(name), null, null, null)
        // select * from Persons where Name = name
        if (c.count < 1) {
            return "not exist"
        }
        c.moveToFirst()
        s = c.getString(c.getColumnIndex("Location"))
        return s
    }

    fun updateval(s4: String, s5: String?): Int {
        val contentValues = ContentValues()
        contentValues.put("Location", s5)
        return sqLiteDatabase.update("Persons", contentValues, "Name = '$s4'", null)

        //  update table persons set Location='s5' where Name =  s4
    }
}