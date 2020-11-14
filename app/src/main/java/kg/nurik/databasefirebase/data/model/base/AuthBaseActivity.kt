package kg.nurik.databasefirebase.data.model.base

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kg.nurik.databasefirebase.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.concurrent.TimeUnit

abstract class AuthBaseActivity:AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    protected var storedVerificationId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        auth = Firebase.auth
        setupCallback()
    }

    protected fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
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

    protected fun startTimer() {
        btnSentPhone.isEnabled = false

        val timer = object : CountDownTimer(60_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
//
//                dateFormat(millisUntilFinished)
                val seconds = (millisUntilFinished / 1000).toInt()
                tvTimer.text = "stolko to sekund 00: $seconds"
                tvTimer.visibility = View.VISIBLE
            }

            override fun onFinish() {
                btnSentPhone.isEnabled = true
                tvTimer.visibility = View.GONE
            }
        }
        timer.start()
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

    protected fun verifyPhone(phone: String) {
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