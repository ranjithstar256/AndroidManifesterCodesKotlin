package ran.am.androidmanifestercodeskotlin

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DataTimePicker : AppCompatActivity() {
    var datePickerDialog: DatePickerDialog? = null
    var timePickerDialog: TimePickerDialog? = null
    var ed1: EditText? = null
    var ed2: EditText? = null

    //ElasticDownloadView elasticDownloadView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_time_picker)
        ed1 = findViewById(R.id.editTextTextPersonName2)
        ed2 = findViewById(R.id.editTextTextPersonName3)

        //  elasticDownloadView = findViewById(R.id.elastic_download_view);
        ///  elasticDownloadView.startIntro();
        //  elasticDownloadView.setProgress(25);
    }

    fun datee(view: View?) {
        datePickerDialog = DatePickerDialog(this@DataTimePicker, onDateSetListener, 2010, 0, 6)
        datePickerDialog!!.show()

        //elasticDownloadView.success(); //This function moves the cursor to 100 if the progress has not been set already
        //elasticDownloadView.fail();
    }

    var onDateSetListener = OnDateSetListener { view, year, month, dayOfMonth ->
        Toast.makeText(this@DataTimePicker, "$year/$month/$dayOfMonth", Toast.LENGTH_SHORT).show()
        ed1!!.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
    }

    fun timee(view: View?) {
        timePickerDialog = TimePickerDialog(this@DataTimePicker, onTimeSetListener, 2, 10, true)
        timePickerDialog!!.show()
    }

    var onTimeSetListener = OnTimeSetListener { view, hourOfDay, minute ->
        Toast.makeText(this@DataTimePicker, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
        ed2!!.setText("$hourOfDay:$minute")
    }
}