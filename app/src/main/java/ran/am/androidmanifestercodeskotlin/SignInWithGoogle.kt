package ran.am.androidmanifestercodeskotlin

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

class SignInWithGoogle : AppCompatActivity() {
    private var oneTapClient: SignInClient? = null
    private var signInRequest: BeginSignInRequest? = null
    private var mAuth: FirebaseAuth? = null
    var TAG = "TAGabc123"
    private val showOneTapUI = true
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth?.getCurrentUser()
        // updateUI(currentUser);
        Log.i("TAGabc123", "onStart: $currentUser")
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_with_google)
        mAuth = FirebaseAuth.getInstance()
        oneTapClient = Identity.getSignInClient(this)
        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true) // Your server's client ID, not your Android client ID.
                    //.setServerClientId(getString(R.string.default_web_client_id)) // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            ) // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()
        oneTapClient!!.beginSignIn(signInRequest!!)
            .addOnSuccessListener(this, object : OnSuccessListener<BeginSignInResult?> {
                @JvmName("onSuccess1")
                fun onSuccess(result: BeginSignInResult) {
                    try {
                        startIntentSenderForResult(
                            result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
                            null, 0, 0, 0
                        )
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e(TAG, "Couldn't start One Tap UI: " + e.getLocalizedMessage())
                    }
                }

                override fun onSuccess(p0: BeginSignInResult?) {
                    TODO("Not yet implemented")
                }
            })
            .addOnFailureListener(this, object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    // No saved credentials found. Launch the One Tap sign-up flow, or
                    // do nothing and continue presenting the signed-out UI.
                    Log.d(TAG, e.localizedMessage)
                }
            })
    }

    fun goglesignin(view: View?) {
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true) // Your server's client ID, not your Android client ID.
                    //.setServerClientId("349889351293-tsj8s2vl55lvhd3e5aogbeshb705tiq2.apps.googleusercontent.com")
                    .setServerClientId("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") // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_ONE_TAP -> try {
                val credential: SignInCredential? =  oneTapClient?.getSignInCredentialFromIntent(data)
                val idToken: String? = credential?.getGoogleIdToken()
                val username: String? = credential?.getId()
                val password: String? = credential?.getPassword()
                if (idToken != null) {
                    // Got an ID token from Google. Use it to authenticate
                    // with your backend.
                    Log.d(TAG, "Got ID token.$username")
                } else if (password != null) {
                    // Got a saved username and password. Use them to authenticate
                    // with your backend.
                    Log.d(TAG, "Got password.$password")
                }
            } catch (e: ApiException) {
                // ...
            }
        }
    }

    companion object {
        private const val REQ_ONE_TAP = 2 // Can be any integer unique to the Activity.
    }
}