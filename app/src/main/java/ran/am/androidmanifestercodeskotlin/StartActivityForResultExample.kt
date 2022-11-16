package ran.am.androidmanifestercodeskotlin

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartActivityForResultExample : AppCompatActivity() {
    var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_for_result_example)
        imageView = findViewById(R.id.imageView)
    }

    fun opencamm(view: View?) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 66)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val myBitmap: Bitmap?
        if (data == null) {
            Toast.makeText(this, "nul", Toast.LENGTH_SHORT).show()
        } else {
            myBitmap = data.extras!!["data"] as Bitmap?
            imageView!!.setImageBitmap(myBitmap)
        }
    }
}