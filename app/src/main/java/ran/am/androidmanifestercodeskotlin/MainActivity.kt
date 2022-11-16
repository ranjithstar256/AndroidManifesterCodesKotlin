package ran.am.androidmanifestercodeskotlin

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var arrayList: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayList = ArrayList()
        arrayList!!.add("Simple Button Click Event and Menu Example") //0
        arrayList!!.add("Getting data from EditText") //1
        arrayList!!.add("Passing Data Between Activity") //2
        arrayList!!.add("ListView Example") //3
        arrayList!!.add("Widgets Radio checkbox") //4
        arrayList!!.add("Date Time Picker") //5
        arrayList!!.add("Audio Video in Example") //6
        arrayList!!.add("Start Activity for Result Example") //7
        arrayList!!.add("Alert and Custom Dialogs") //8
        arrayList!!.add("WebView Example") //9
        arrayList!!.add("Turn On Off Wifi Bluetooth") //10
        arrayList!!.add("Gmap Find current Location") //11
        arrayList!!.add("SharedPreference Example") //12
        arrayList!!.add("Notification Example") //13
        arrayList!!.add("Internal External Storage Example") //14
        arrayList!!.add("Activity Life Cycle Example") //15
        arrayList!!.add("Animation in Android") //16
        arrayList!!.add("Sending Sms and Mail") //17
        arrayList!!.add("Sensor Example") //18
        arrayList!!.add("Android Service") //19
        arrayList!!.add("Fragment Example") //20
        arrayList!!.add("Fragment with Navigation") //21
        arrayList!!.add("SQLite Database! CURD operation") //22
        arrayList!!.add("Internet Based App - Getting data from API/URL using REST API") //23
        arrayList!!.add("RecyclerView") //24
        arrayList!!.add("Retrofit Example") //25
        arrayList!!.add("Ola Uber Swiggy\nLive location\nTracking") //26
        arrayList!!.add("Work Manager Example") //27
        arrayList!!.add("Simple ROOM Database Example") //28
        arrayList!!.add("Livedata Example") //29
        arrayList!!.add("MVVM Example") //30

        val arrayAdapter: ArrayAdapter<*>

        var mListView = findViewById<ListView>(R.id.idlistv)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arrayList!!
        )
        mListView.adapter = arrayAdapter

        mListView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->

            when (position) {
                0 -> startActivity(
                    Intent(
                        this@MainActivity,
                        SimpleButtonClickEventListener::class.java
                    )
                )
                1 -> startActivity(Intent(this@MainActivity, GettingDatafromEditText::class.java))
                2 -> startActivity(Intent(this@MainActivity, PassingDataBwActivity::class.java))
                3 -> startActivity(Intent(this@MainActivity, ListViewExample::class.java))
                4 -> startActivity(Intent(this@MainActivity, WidgetsRadioCheck::class.java))
                5 -> startActivity(Intent(this@MainActivity, DataTimePicker::class.java))
                6 -> startActivity(Intent(this@MainActivity, AudioVideoExample::class.java))
                7 -> startActivity(Intent(this@MainActivity, StartActivityForResultExample::class.java))
                8 -> startActivity(Intent(this@MainActivity, SimpleAlertDialog::class.java))
                9 -> startActivity(Intent(this@MainActivity, WebviewExample::class.java))
                10 -> startActivity(Intent(this@MainActivity, WifiBluetooth::class.java))
                11 -> startActivity(Intent(this@MainActivity, MapsExpl::class.java))
                12 -> startActivity(Intent(this@MainActivity, SharedPreferenceExample::class.java))
                13 -> startActivity(Intent(this@MainActivity, NotificationExample::class.java))
                14 -> startActivity(Intent(this@MainActivity, InternalExternalStorage::class.java))
                15 -> startActivity(Intent(this@MainActivity, ActivityLifeCycleExample::class.java))
                16 -> startActivity(Intent(this@MainActivity, AnimationInAndroid::class.java))
                17 -> startActivity(Intent(this@MainActivity, SendingSmsMail::class.java))
                18 -> startActivity(Intent(this@MainActivity, SensorsInAndroid::class.java))
                19 -> startActivity(Intent(this@MainActivity, AndroidServiceExample::class.java))
                20 -> startActivity(Intent(this@MainActivity, FragmentsExample::class.java))
                21 -> startActivity(Intent(this@MainActivity, NavigationActivity::class.java))
                22 -> startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                23 -> startActivity(Intent(this@MainActivity, WebBased::class.java))
                24 -> startActivity(Intent(this@MainActivity, ReCycleViewExpl::class.java))
                25 -> startActivity(Intent(this@MainActivity, SimpleRetrofitExpl::class.java))
                26 -> {
                    startActivity(Intent(this@MainActivity, GmapLiveTracking::class.java))
                    Toast.makeText(
                        this@MainActivity,
                        "it will keep updating when your location changes",
                        Toast.LENGTH_LONG
                    ).show()
                }
                27 -> startActivity(Intent(this@MainActivity, WorkMngrExpl::class.java))
               // 28 -> startActivity(
                  //  Intent(
                     //   this@MainActivity,
                    //    kp.ranjith.notesroom.MainActivity::class.java
                   // )
              //  )
              //  29 ->// startActivity(Intent(this@MainActivity, MainActivity2::class.java))
               /// 30 -> {
                 //   startActivity(
                     //   Intent(
                      //      this@MainActivity,
                      //      kp.ranjith.s3_v2_viewmodeldemo.MainActivity::class.java
                     //   )
                  //  )
//                    Toast.makeText(
//                        this@MainActivity,
//                        "even if rotate the device , data is not lost",
//                        Toast.LENGTH_LONG
//                    ).show()
                }
           // }
        })


    }
}