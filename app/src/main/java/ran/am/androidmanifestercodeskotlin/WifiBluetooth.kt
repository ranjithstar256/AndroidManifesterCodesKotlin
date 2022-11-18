package ran.am.androidmanifestercodeskotlin

import android.Manifest
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
import android.provider.Settings
import android.view.View
import android.widget.*

class WifiBluetooth : AppCompatActivity() {
    lateinit var blu: Switch
    lateinit var wifibtn: Button
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {


                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(
                        this@WifiBluetooth,
                        "Permission denied to change bluetooth status",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_bluetooth)
        blu = findViewById(R.id.switch1)
        wifibtn = findViewById(R.id.btn)
        ActivityCompat.requestPermissions(
            this@WifiBluetooth, arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
            1
        )
        blu.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (isChecked) {
                if (ActivityCompat.checkSelfPermission(
                        this@WifiBluetooth,
                        Manifest.permission.BLUETOOTH_CONNECT
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    return@OnCheckedChangeListener
                }
                mBluetoothAdapter.enable()
            } else {
                mBluetoothAdapter.disable()
            }
        })
        wifibtn.setOnClickListener(View.OnClickListener { //   startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            val panel = Intent(Settings.Panel.ACTION_WIFI)
            startActivity(panel)
        })

        /*   wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                    startActivityForResult(panelIntent, 0);
                } else {
                    // add appropriate permissions to AndroidManifest file (see https://stackoverflow.com/questions/3930990/android-how-to-enable-disable-wifi-or-internet-connection-programmatically/61289575)
                    wifi.setWifiEnabled(true);
                }
            }
        });*/
    }
}