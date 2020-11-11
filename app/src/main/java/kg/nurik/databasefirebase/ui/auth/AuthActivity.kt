package kg.nurik.databasefirebase.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.concurrent.TimeUnit

class AuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        auth = Firebase.auth
        setupCallback()
        setupListeners()
    }

    private fun setupListeners() {
        btnSentPhone.setOnClickListener {
            verifyPhone(etInputNumber.text.toString())
        }

        btnSentCode.setOnClickListener {
            storedVerificationId?.let { verificationId ->
                val credential = PhoneAuthProvider.getCredential(
                    verificationId,
                    etInputCode.text.toString()
                )
                signInWithPhoneAuthCredential(credential)
            }
        }
    }


    private fun setupCallback() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(applicationContext, "onVerificationCompleted", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(applicationContext, "onVerificationFailed", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                p1: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, p1)
                storedVerificationId = verificationId
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.getIdToken(true)?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val idToken: String? = it.result?.token
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    }
                } else {
                    Log.w("adasdasdasd", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                }
            }
    }

    private fun verifyPhone(phone: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
//          .setPhoneNumber("+996508170177")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}