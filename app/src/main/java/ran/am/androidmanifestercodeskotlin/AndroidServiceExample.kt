package ran.am.androidmanifestercodeskotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AndroidServiceExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_service_example)
    }

    fun startservce(view: View?) {
        val intent = Intent(this@AndroidServiceExample, MyService::class.java)
        startService(intent)
    }

    fun stoptservce(view: View?) {
        val intent = Intent(this@AndroidServiceExample, MyService::class.java)
        stopService(intent)
    }
}