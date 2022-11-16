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
import java.util.ArrayList

class WebBased : AppCompatActivity() {
    var textView: TextView? = null
    var textView2: TextView? = null
    var s1: String? = null
    var s2: String? = null
    var jsonstr: String? = null
    var reslt: String? = null
    var progressDialog: ProgressDialog? = null
    var whl: JSONObject? = null
    var obj7: JSONObject? = null
    var obj8: JSONObject? = null
    var jsonArray: JSONArray? = null
    var listView: ListView? = null
    var arrayList: ArrayList<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_based)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
        listView = findViewById(R.id.lis)
        arrayList = ArrayList<Any?>()
    }

    fun getcont(view: View?) {
        val getContacts = GetContacts()
        getContacts.execute()
    }

    private inner class GetContacts : AsyncTask<Any?, Any?, Any?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@WebBased)
            progressDialog!!.setMessage("Please wait")
            progressDialog!!.show()
        }

        override fun doInBackground(params: Array<Any?>): Any? {
            val ragu = Ragu()
            jsonstr = ragu.abc("https://mocki.io/v1/19154358-a8f2-4c39-8333-8e932f833873")
            try {
                whl = JSONObject(jsonstr)
                val widgOb = whl!!.getJSONObject("widget")
                val windowObj = widgOb.getJSONObject("window")
                reslt = windowObj.getString("title")
                val imgObj = widgOb.getJSONObject("image")
                s2 = imgObj.getString("name")

                /*  for (int i = 0; i < jsonArray.length(); i++) {
                    obj7 = jsonArray.getJSONObject(i);
                    arrayList.add(obj7.getString("name") + "\n");
                }*/
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return null
        }

        public override fun onPostExecute(o: Any?) {
            super.onPostExecute(o)
            progressDialog!!.dismiss()
            textView!!.text = reslt
            textView2!!.text = s2
            // ArrayAdapter arrayAdapter = new ArrayAdapter(WebBased.this, android.R.layout.simple_list_item_1, arrayList);

            /// listView.setAdapter(arrayAdapter);
        }
    }
}