package ran.am.androidmanifestercodeskotlin

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

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