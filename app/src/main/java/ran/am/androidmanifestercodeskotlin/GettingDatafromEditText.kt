package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GettingDatafromEditText : AppCompatActivity() {
    lateinit var ed1: EditText
    lateinit  var ed2: EditText
    lateinit var s: String
    lateinit var tv: TextView
    lateinit var tv2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getting_datafrom_edit_text)
        ed1 = findViewById(R.id.editText2)
        ed2 = findViewById(R.id.editTextTextPersonName)
        tv = findViewById(R.id.textView)
        tv2 = findViewById(R.id.textView8)
        ed2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                tv2.setText(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun somename(view: View?) {
        s = ed1!!.text.toString()
        tv!!.text = s
    }
}