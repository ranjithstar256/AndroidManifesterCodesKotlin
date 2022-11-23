package ran.am.androidmanifestercodeskotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
// test
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "charger connected", Toast.LENGTH_SHORT).show()

        // this block will be get executed when the airplane mode got turned ON/OFF
    }
}