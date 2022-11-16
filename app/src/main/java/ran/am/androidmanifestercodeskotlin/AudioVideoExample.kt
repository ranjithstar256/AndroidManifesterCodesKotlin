package ran.am.androidmanifestercodeskotlin

import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class AudioVideoExample : AppCompatActivity() {
    var videoView: VideoView? = null
    var MY_VIDEO = "https://www.ebookfrenzy.com/android_book/movie.mp4"
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_video_example)
        mediaPlayer = MediaPlayer.create(
            this@AudioVideoExample,
            R.raw.vanthematharam
        ) /// audio file name small letter
        videoView = findViewById(R.id.videoView)
    }

    fun startt(view: View?) {
        mediaPlayer!!.start()
    }

    fun pausee(view: View?) {
        mediaPlayer!!.pause()
    }

    fun stopp(view: View?) {
        mediaPlayer!!.stop()
    }

    fun startvid(view: View?) {
        val internet = checkinternet() // this is the coding for checking internet connection
        if (internet) {
            Toast.makeText(
                this@AudioVideoExample,
                "Video starting.. please wait..",
                Toast.LENGTH_SHORT
            ).show()
            val videoview = findViewById<View>(R.id.videoView) as VideoView
            //Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test);
            videoview.setVideoPath("https://p.urbanpro.com/tv-prod/video%2Fmember-video%2F2019456%2Fprocessed-video%2F25577-IIT+Hyderabad.mp4")
            videoview.setMediaController(MediaController(this@AudioVideoExample))
            videoview.start()
        } else {
            Toast.makeText(this, "Internet unavailable", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkinternet(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
    }
}