package ran.am.androidmanifestercodeskotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class NotificationExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_example)
    }

    fun shownvofn(view: View?) {
//this code is for before Oreo
        // 1. create a Intent
        // 2. create a PendingIntent
        // 3. design Notification
        // 4. initilaze NotifivstionManager
        val intent = Intent(this@NotificationExample, NotificationExample::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this@NotificationExample,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        val notification = Notification.Builder(this@NotificationExample)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("New message received")
            .setContentIntent(pendingIntent)
            .setContentText("message from Ragu")
            .build()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }

    fun shownofn(view: View?) {
        val notificationManager =
            this@NotificationExample.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "channel-01"
        val channelName = "Channel Name"
        val importance = NotificationManager.IMPORTANCE_HIGH
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
        }
        val mBuilder = NotificationCompat.Builder(this@NotificationExample, channelId)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this@NotificationExample.resources,
                    R.drawable.logo
                )
            )
            .setSmallIcon(R.drawable.logo).setContentTitle("You can also 'Learn Android'")
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(
                        this@NotificationExample.resources,
                        R.drawable.logo
                    )
                )
            )
            .setContentText("Contact AndroidManifester today!!")
        val intent = Intent(this@NotificationExample, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this@NotificationExample,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        mBuilder.setContentIntent(pendingIntent)
        notificationManager.notify(notificationId, mBuilder.build())
    }
}