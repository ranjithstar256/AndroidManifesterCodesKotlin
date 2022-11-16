package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* this code example is from Simplified coding Belal Khan bro! Credits to him :) I use some more code examples of him as well */
class SimpleRetrofitExpl : AppCompatActivity() {
    var listView: ListView? = null
    var TAG = "abc123abc"
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_retrofit_expl)
        listView = findViewById<ListView>(R.id.listViewHeroes)

        //calling the method to display the heroes
        heroes
    }
    // Log.i(TAG, "onResponse: "+response.body().toString());

    //Creating an String array for the ListView
    private val heroes: Unit

    //looping through all the heroes and inserting the names inside the string array

        //displaying the string array into listview
        private get() {
            val call: Call<List<Hero>> =
                RetrofitClient.Companion.getInstance().getMyApi().getHeroes()
            call.enqueue(object : Callback<List<Hero?>?> {
                override fun onResponse(call: Call<List<Hero?>?>?, response: Response<List<Hero?>?>) {
                    val heroList: List<Hero> = response.body() as List<Hero>

                    // Log.i(TAG, "onResponse: "+response.body().toString());

                    //Creating an String array for the ListView
                    val heroes = arrayOfNulls<String>(heroList.size)

                    //looping through all the heroes and inserting the names inside the string array
                    for (i in heroList.indices) {
                        heroes[i] = heroList[i].name
                    }

                    //displaying the string array into listview
                    listView!!.adapter = ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        heroes
                    )
                }

                override fun onFailure(call: Call<List<Hero?>?>?, t: Throwable) {
                    Toast.makeText(getApplicationContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<Hero?>?>) {

}
