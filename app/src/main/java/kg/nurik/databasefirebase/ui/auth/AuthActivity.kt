package kg.nurik.databasefirebase.ui.auth

import android.os.Bundle
import com.google.firebase.auth.PhoneAuthProvider
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.data.model.base.AuthBaseActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AuthBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setupListeners()
    }

    private fun setupListeners() {
        btnSentPhone.setOnClickListener {
            verifyPhone(etInputNumber.text.toString())
            startTimer()
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
}