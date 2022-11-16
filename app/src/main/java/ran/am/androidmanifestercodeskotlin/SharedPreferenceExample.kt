package ran.am.androidmanifestercodeskotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SharedPreferenceExample : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var ed1: EditText? = null
    var ed2: EditText? = null
    var s1: String? = null
    var s2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference_example)
        sharedPreferences = getSharedPreferences("sfname", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        ed1 = findViewById(R.id.editText)
        ed2 = findViewById(R.id.editText2)
    }

    fun save1(view: View?) {
        s1 = ed1!!.text.toString()
        s2 = ed2!!.text.toString()
        editor!!.putString("key1", s1)
        editor!!.putString("key2", s2)
        editor!!.commit()
        startActivity(Intent(this@SharedPreferenceExample, GetSharedPrefData::class.java))

        // once a data stored in shared preference , we can get from any activity!
        //lets get it from GetSharedPrefData  class
    }
}