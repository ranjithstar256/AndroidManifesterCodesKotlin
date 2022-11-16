package ran.am.androidmanifestercodeskotlin

import android.content.ContentValues
import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

/**
 * Created by Ranjith on 24-06-2017.
 */
class Ragu {
    var response = ""
    fun abc(reqUrl: String?): String {
        try {
            val url = URL(reqUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            // read the response
            val `in`: InputStream = BufferedInputStream(conn.inputStream)
            response = convertStreamToString(`in`)
        } catch (e: MalformedURLException) {
            Log.e(ContentValues.TAG, "MalformedURLException: " + e.message)
        } catch (e: ProtocolException) {
            Log.e(ContentValues.TAG, "ProtocolException: " + e.message)
        } catch (e: IOException) {
            Log.e(ContentValues.TAG, "IOException: " + e.message)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: " + e.message)
        }
        return response
    }

    private fun convertStreamToString(`is`: InputStream): String {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append('\n')
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }
}