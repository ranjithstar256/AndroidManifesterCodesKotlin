package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WidgetsRadioCheck : AppCompatActivity() {
    lateinit var b1: Button
    lateinit var radioGroup: RadioGroup
    lateinit var cb: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets_radio_check)
        b1 = findViewById(R.id.button4)
        radioGroup = findViewById(R.id.rg)
        cb = findViewById(R.id.checkBox)
        b1.setOnClickListener(View.OnClickListener {
            val id = radioGroup.getCheckedRadioButtonId()
            when (id) {
                R.id.button -> Toast.makeText(this@WidgetsRadioCheck, "Male", Toast.LENGTH_SHORT)
                    .show()
                R.id.button2 -> Toast.makeText(this@WidgetsRadioCheck, "Female", Toast.LENGTH_SHORT)
                    .show()
                R.id.button3 -> Toast.makeText(
                    this@WidgetsRadioCheck,
                    "Transgender",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (cb.isChecked()) {
                Toast.makeText(this@WidgetsRadioCheck, "Terms Agreed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@WidgetsRadioCheck, "Terms not Agreed", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}