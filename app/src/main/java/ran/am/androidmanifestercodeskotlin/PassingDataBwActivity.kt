package ran.am.androidmanifestercodeskotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PassingDataBwActivity : AppCompatActivity() {
    var ed1: EditText? = null
    var ed2: EditText? = null
    var s1: String? = null
    var s2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passing_data_bw)
        ed1 = findViewById(R.id.editText)
        ed2 = findViewById(R.id.editText3)
    }

    fun methodnam(view: View?) {
        s1 = ed1!!.text.toString()
        s2 = ed2!!.text.toString()
        val intent = Intent(this@PassingDataBwActivity, DestinationActivity::class.java)

        // intent.putExtra( "anytext" ,value needs to be passed);
        intent.putExtra("anytext", s1)
        intent.putExtra("anothertext", s2)
        startActivity(intent)
    }
}