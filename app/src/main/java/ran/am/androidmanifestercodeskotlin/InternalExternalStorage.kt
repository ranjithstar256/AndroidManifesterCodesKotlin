package ran.am.androidmanifestercodeskotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.*

class InternalExternalStorage : AppCompatActivity() {
    lateinit   var editTextFileName: EditText
    lateinit  var editTextData: EditText
    lateinit  var saveInternalButton: Button
    lateinit  var readinternalButton: Button
    lateinit var saveExternalButton: Button
    lateinit  var readExternalButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_external_storage)
        editTextFileName = findViewById(R.id.editText1)
        editTextData = findViewById(R.id.editText2)
        saveInternalButton = findViewById(R.id.button1)
        readinternalButton = findViewById(R.id.button2)
        saveExternalButton = findViewById(R.id.button14)
        readExternalButton = findViewById(R.id.button17)
        ActivityCompat.requestPermissions(
            this@InternalExternalStorage, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
        if (!isExternalStorageAvailable || isExternalStorageReadOnly) {
            saveExternalButton.setEnabled(false)
            Toast.makeText(this, "Storage unavailable", Toast.LENGTH_SHORT).show()
        }

        //Performing Action on Read Button
        saveInternalButton.setOnClickListener(View.OnClickListener {
            val filename = editTextFileName.getText().toString()
            val data = editTextData.getText().toString()
            val fos: FileOutputStream
            try {
                fos = openFileOutput(filename, MODE_PRIVATE)
                fos.write(data.toByteArray())
                fos.close()
                Toast.makeText(
                    applicationContext, "$filename saved Internal",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })

        //Performing Action on Read Button
        readinternalButton.setOnClickListener(View.OnClickListener {
            val filename = editTextFileName.getText().toString()
            val stringBuffer = StringBuffer()
            try {
                //Attaching BufferedReader to the FileInputStream by the help of InputStreamReader
                val inputReader = BufferedReader(
                    InputStreamReader(
                        openFileInput(filename)
                    )
                )
                var inputString: String
                //Reading data line by line and storing it into the stringbuffer
                while (inputReader.readLine().also { inputString = it } != null) {
                    stringBuffer.append(
                        """
    $inputString
    
    """.trimIndent()
                    )
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //Displaying data on the toast
            Toast.makeText(
                applicationContext, stringBuffer.toString(),
                Toast.LENGTH_LONG
            ).show()
        })
        saveExternalButton.setOnClickListener(View.OnClickListener {
            val filename = editTextFileName.getText().toString()
            val data = editTextData.getText().toString()
            try {
                //File myFile = new File("/sdcard/"+filename);
                /// File myFile = new File(Environment.getExternalStorageState()+filename);
                val directory = filesDir //or getExternalFilesDir(null); for external storage
                val file = File(directory, filename)
                file.createNewFile()
                val fOut = FileOutputStream(file)
                val myOutWriter = OutputStreamWriter(fOut)
                myOutWriter.append(data)
                myOutWriter.close()
                fOut.close()
                Toast.makeText(applicationContext, filename + "saved External", Toast.LENGTH_LONG)
                    .show()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })

        //Performing action on Read Button
        readExternalButton.setOnClickListener(View.OnClickListener {
            val filename = editTextFileName.getText().toString()
            var aDataRow = ""
            var aBuffer = ""
            try {
                //File myFile = new File("/sdcard/"+filename);
//                    File myFile = new File(getFilesDir()+filename);
                val directory = filesDir //or getExternalFilesDir(null); for external storage
                val file = File(directory, filename)
                val fIn = FileInputStream(file)
                val myReader = BufferedReader(
                    InputStreamReader(fIn)
                )
                while (myReader.readLine().also { aDataRow = it } != null) {
                    aBuffer += """
                        $aDataRow
                        """.trimIndent()
                }
                myReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, aBuffer, Toast.LENGTH_LONG).show()
        })
    }

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
                        this@InternalExternalStorage,
                        "Permission denied to read your External storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    companion object {
        private val isExternalStorageReadOnly: Boolean
            private get() {
                val extStorageState = Environment.getExternalStorageState()
                return if (Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState) {
                    true
                } else false
            }
        private val isExternalStorageAvailable: Boolean
            private get() {
                val extStorageState = Environment.getExternalStorageState()
                return if (Environment.MEDIA_MOUNTED == extStorageState) {
                    true
                } else false
            }
    }
}