package ran.am.androidmanifestercodeskotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetSharedPrefData : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var s1: String
    lateinit var s2: String
    lateinit var editor: SharedPreferences.Editor
    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_shared_pref_data)
        sharedPreferences = getSharedPreferences("sfname", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        tv = findViewById(R.id.textView6)
    }

    fun getsh(view: View?) {
        s1 = sharedPreferences!!.getString("key1", "def val").toString()
        s2 = sharedPreferences!!.getString("key2", "def val").toString()
        tv!!.text = """
               $s1
               $s2
               """.trimIndent()
    }
}