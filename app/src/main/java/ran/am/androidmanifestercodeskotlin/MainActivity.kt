package ran.am.androidmanifestercodeskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.ArrayList

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


        // access the listView from xml file
        var mListView = findViewById<ListView>(R.id.idlistv)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arrayList!!
        )
        mListView.adapter = arrayAdapter
    }
}