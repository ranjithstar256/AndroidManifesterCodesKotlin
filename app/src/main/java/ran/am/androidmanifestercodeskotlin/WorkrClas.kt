package ran.am.androidmanifestercodeskotlin

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkrClas(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    /**
     * Override this method to do your actual background processing.  This method is called on a
     * background thread - you are required to **synchronously** do your work and return the
     * [Result] from this method.  Once you return from this
     * method, the Worker is considered to have finished what its doing and will be destroyed.
     *
     *
     * A Worker is given a maximum of ten minutes to finish its execution and return a
     * [Result].  After this time has expired, the Worker will
     * be signalled to stop.
     *
     * @return The [Result] of the computation; note that
     * dependent work will not execute if you use
     * [Result.failure] or
     */
    override fun doWork(): Result {
        val context: Context = getApplicationContext()
        return try {
            Log.d(TAG, "doWork Called")
            UtilsClas.sendNotification(context)
            Result.success()
        } catch (throwable: Throwable) {
            Log.d(TAG, "Error Sending Notification" + throwable.message)
            Result.failure()
        }
    }

    companion object {
        private val TAG = WorkrClas::class.java.name
    }
}