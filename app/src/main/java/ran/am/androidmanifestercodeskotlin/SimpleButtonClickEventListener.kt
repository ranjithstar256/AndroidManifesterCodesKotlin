package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SimpleButtonClickEventListener : AppCompatActivity() {
    // ITS VERY MUST!!! Always follow Declaration above oncreate method
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_button_click_event_listener)
        // ITS VERY MUST!!! Always follow Initialization inside oncreate method
        btn = findViewById(R.id.button)

        //usage
        btn.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                this@SimpleButtonClickEventListener,
                "btn got clicked",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    fun anyname(view: View?) {
        Toast.makeText(this, "button got clicked", Toast.LENGTH_SHORT).show()
    }

    //ADDING MENU CODE
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menefilenam, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int
        id = item.itemId
        when (id) {
            R.id.idfile -> Toast.makeText(this, "file selected", Toast.LENGTH_SHORT).show()
            R.id.idopen -> Toast.makeText(this, "open selected", Toast.LENGTH_SHORT).show()
            R.id.idsave -> Toast.makeText(this, "save selected", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}