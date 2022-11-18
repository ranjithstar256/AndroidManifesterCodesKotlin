package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.work.*
import java.util.concurrent.TimeUnit


class WorkMngrExpl : AppCompatActivity() {
    lateinit var workManager: WorkManager
    lateinit var btnStartOneTimeRequest: AppCompatButton
    lateinit  var btnStartPeriodicRequest: AppCompatButton
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_mngr_expl)
        workManager = WorkManager.getInstance(this)
   //     btnStartOneTimeRequest = findViewById<AppCompatButton>(R.id.button18)
   //      btnStartPeriodicRequest = findViewById<AppCompatButton>(R.id.button19)
    }


    fun onetm(view: View?) {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val myWorkRequest: WorkRequest =  OneTimeWorkRequestBuilder<WorkrClas>()
                ///.setConstraints(constraints)
                .build()

        workManager.enqueue(myWorkRequest)
        Toast.makeText(applicationContext,"",Toast.LENGTH_SHORT).show()

    }

    fun perdctm(view: View?) {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val work = PeriodicWorkRequestBuilder<WorkrClas>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        //val workManager = WorkManager.getInstance(applicationContext)

        workManager.enqueue(work)
    }


}