package ran.am.androidmanifestercodeskotlin

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SensorsInAndroid : AppCompatActivity() {
    lateinit var sm: SensorManager
    lateinit  var textView1: TextView
    var list: List<*>? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors_in_android)
        sm = getSystemService(SENSOR_SERVICE) as SensorManager
        textView1 = findViewById(R.id.textView1)
        list = sm!!.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if ((list as MutableList<Sensor>?)?.size ?:  > 0) {
            sm!!.registerListener(sel, (list as MutableList<Sensor>?)?.get(0) as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(baseContext, "Error: No Accelerometer.", Toast.LENGTH_LONG).show()
        }
    }

    var sel: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            val values = event.values
            textView1!!.text = """
                x: ${values[0]}
                y: ${values[1]}
                z: ${values[2]}
                """.trimIndent()
        }
    }
}