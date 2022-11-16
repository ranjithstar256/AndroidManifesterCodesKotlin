package ran.am.androidmanifestercodeskotlin

import android.os.Bundle
import android.view.View
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
        workManager = WorkManager.getInstance()
        btnStartOneTimeRequest = findViewById<AppCompatButton>(R.id.button18)
        btnStartPeriodicRequest = findViewById<AppCompatButton>(R.id.button19)
    }

    fun onetm(view: View?) {
        val constraints: Constraints = Builder() //.setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeWorkRequest: OneTimeWorkRequest =
            Builder(WorkrClas::class.java) //   .setConstraints(constraints)
                .build()
        workManager.enqueue(oneTimeWorkRequest)
    }

    fun perdctm(view: View?) {
        val constraints: Constraints = Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val periodicWorkRequest: PeriodicWorkRequest = Builder(
            WorkrClas::class.java, 24, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()
        workManager?.enqueue(periodicWorkRequest)
    }
}