package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinationActivity : AppCompatActivity() {
//    var t1: TextView? = null
//    var t2: TextView? = null

    lateinit var t1: TextView
    lateinit var t2: TextView

    var s1: String? = null
    var s2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)
        t1 = findViewById(R.id.textView2)
        t2 = findViewById(R.id.textView3)
        s1 = intent.getStringExtra("anytext")
        s2 = intent.getStringExtra("anothertext")
        t1.setText(s1)
        t2.setText(s2)
    }
}