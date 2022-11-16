package ran.am.androidmanifestercodeskotlin

import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.os.Bundle
import ran.am.androidmanifestercodeskotlin.R
import android.content.Intent
import ran.am.androidmanifestercodeskotlin.GetSharedPrefData
import android.app.ProgressDialog
import org.json.JSONObject
import org.json.JSONArray

import android.os.AsyncTask
import ran.am.androidmanifestercodeskotlin.Ragu
import org.json.JSONException
import ran.am.androidmanifestercodeskotlin.NotificationExample
import android.app.PendingIntent
import android.app.NotificationManager
import android.app.NotificationChannel
import androidx.core.app.NotificationCompat
import android.graphics.BitmapFactory
import ran.am.androidmanifestercodeskotlin.DBHelper
import ran.am.androidmanifestercodeskotlin.DestinationActivity
import android.provider.MediaStore
import android.graphics.Bitmap
import androidx.fragment.app.FragmentActivity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import ran.am.androidmanifestercodeskotlin.FirstFragment
import ran.am.androidmanifestercodeskotlin.SecondFragment
import ran.am.androidmanifestercodeskotlin.ThirdFragment
import android.os.Build
import android.content.DialogInterface
import ran.am.androidmanifestercodeskotlin.GmapLiveTracking
import android.content.IntentSender.SendIntentException
import android.app.Activity
import android.text.TextWatcher
import android.text.Editable
import ran.am.androidmanifestercodeskotlin.SignInWithGoogle
import ran.am.androidmanifestercodeskotlin.InternalExternalStorage
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.media.MediaPlayer
import android.net.ConnectivityManager
import ran.am.androidmanifestercodeskotlin.Hero
import ran.am.androidmanifestercodeskotlin.RetrofitClient
import kotlin.jvm.Synchronized
import ran.am.androidmanifestercodeskotlin.MyListData
import androidx.recyclerview.widget.RecyclerView
import android.widget.AdapterView.OnItemClickListener
import android.view.animation.Animation
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.content.BroadcastReceiver
import androidx.appcompat.widget.AppCompatButton
import ran.am.androidmanifestercodeskotlin.WorkrClas
import ran.am.androidmanifestercodeskotlin.UtilsClas
import android.bluetooth.BluetoothAdapter
import android.hardware.SensorManager
import android.hardware.SensorEventListener
import android.hardware.SensorEvent
import ran.am.androidmanifestercodeskotlin.MyService
import ran.am.androidmanifestercodeskotlin.MyRecyclerViewAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import ran.am.androidmanifestercodeskotlin.MyRecyclerViewAdapter.ViewHolderClass
import android.os.IBinder
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.view.View
import android.widget.*

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