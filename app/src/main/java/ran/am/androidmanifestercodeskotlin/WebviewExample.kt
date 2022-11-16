package ran.am.androidmanifestercodeskotlin

import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.widget.EditText
import android.os.Bundle
import ran.am.androidmanifestercodeskotlin.R
import android.content.Intent
import ran.am.androidmanifestercodeskotlin.GetSharedPrefData
import android.widget.TextView
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
import android.widget.Toast
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
import android.widget.VideoView
import android.media.MediaPlayer
import android.net.ConnectivityManager
import ran.am.androidmanifestercodeskotlin.Hero
import ran.am.androidmanifestercodeskotlin.RetrofitClient
import kotlin.jvm.Synchronized
import ran.am.androidmanifestercodeskotlin.MyListData
import androidx.recyclerview.widget.RecyclerView
import android.widget.RelativeLayout
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import android.view.animation.Animation
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.widget.RadioGroup
import android.widget.CheckBox
import android.content.BroadcastReceiver
import androidx.appcompat.widget.AppCompatButton
import ran.am.androidmanifestercodeskotlin.WorkrClas
import ran.am.androidmanifestercodeskotlin.UtilsClas
import android.widget.CompoundButton
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
import android.widget.DatePicker
import android.app.TimePickerDialog.OnTimeSetListener
import android.view.View
import android.widget.TimePicker

class WebviewExample : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var editText: EditText
    lateinit var addr : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_example)
        webView = findViewById(R.id.webv)
        editText = findViewById(R.id.editText4)
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl("https://www.androidmanifester.in")
        webView.setWebChromeClient(object : WebChromeClient() {
            private var mProgress: ProgressDialog? = null
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (mProgress == null) {
                    mProgress = ProgressDialog(this@WebviewExample)
                    mProgress!!.show()
                }
                mProgress!!.setMessage("Loading $progress%")
                if (progress == 100) {
                    mProgress!!.dismiss()
                    mProgress = null
                }
            }
        })
    }

    fun india(view: View?) {
        addr = editText!!.text.toString()
        webView!!.loadUrl(addr!!)
    }
}