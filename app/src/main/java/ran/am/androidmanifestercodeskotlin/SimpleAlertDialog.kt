package ran.am.androidmanifestercodeskotlin

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SimpleAlertDialog : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_alert_dialog)
        alertDialog = AlertDialog.Builder(this@SimpleAlertDialog)
        alertDialog!!.setTitle("this is title")
        alertDialog!!.setMessage("this is message")
        alertDialog!!.setIcon(android.R.drawable.ic_media_play) //using buildin icon
    }

    fun custdia(view: View?) {
// Create custom dialog object
        val dialog = Dialog(this@SimpleAlertDialog)
        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_dialog)
        // Set dialog title
        dialog.setTitle("Custom Dialog")

        // set values for custom dialog components - text, image and button
        val text = dialog.findViewById<TextView>(R.id.textDialog)
        //text.setText("Custom dialog Android example by AndroidManifester");
        dialog.show()
        val declineButton = dialog.findViewById<Button>(R.id.declineButton)
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener { // Close dialog
            dialog.dismiss()
        }
    }

    fun witbtn(view: View?) {
        alertDialog!!.setPositiveButton("Yes", null)
        alertDialog!!.setNegativeButton("No", null) // listener is null (second parameter)

        //adding listener
        alertDialog!!.setNeutralButton("cancel") { dialog, which ->
            Toast.makeText(
                this@SimpleAlertDialog,
                "cancel btn got clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        alertDialog!!.show()
    }

    fun nobtn(view: View?) {
        alertDialog!!.show()
    }
}