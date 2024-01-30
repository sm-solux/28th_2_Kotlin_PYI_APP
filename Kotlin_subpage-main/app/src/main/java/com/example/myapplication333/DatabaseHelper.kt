package com.example.myapplication333

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseHelper {

    private const val URL = "jdbc:mariadb://solux-planyouridea.cv0w6i4ikfrw.ap-northeast-2.rds.amazonaws.com:3306/solux_planyouridea"
    private const val USERNAME = "melitina915"
    private const val PASSWORD = "soluxplanyouridea20240207"

    fun connect(): Connection? {
        var connection: Connection? = null
        try {
            Class.forName("org.mariadb.jdbc.Driver")
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)
        } catch (e: ClassNotFoundException) {
            Log.e("DatabaseHelper", "JDBC Driver not found", e)
        } catch (e: SQLException) {
            Log.e("DatabaseHelper", "Error connecting to the database", e)
        }
        return connection
    }


    fun closeConnection(connection: Connection?) {
        connection?.let {
            try {
                it.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}
