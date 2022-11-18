package ran.am.androidmanifestercodeskotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class WebviewExample : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var editText: EditText
    lateinit var addr : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_example)
        webView = findViewById(R.id.webv)
        editText = findViewById(R.id.editText4)
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl("https://www.androidmanifester.in")
        webView.setWebChromeClient(object : WebChromeClient() {
            private var mProgress: ProgressDialog? = null
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (mProgress == null) {
                    mProgress = ProgressDialog(this@WebviewExample)
                    mProgress!!.show()
                }
                mProgress!!.setMessage("Loading $progress%")
                if (progress == 100) {
                    mProgress!!.dismiss()
                    mProgress = null
                }
            }
        })
    }

    fun india(view: View?) {
        addr = editText!!.text.toString()
        webView!!.loadUrl(addr!!)
    }
}