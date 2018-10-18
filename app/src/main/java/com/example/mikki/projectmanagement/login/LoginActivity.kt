package com.example.mikki.projectmanagement.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.example.mikki.projectmanagement.BuildConfig
import com.example.mikki.projectmanagement.MainActivity
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.LoginInfo
import com.example.mikki.projectmanagement.data.model.LoginUserInfo
import com.example.mikki.projectmanagement.utils.validator.NonEmptyValidator
import com.example.mikki.projectmanagement.viewmodel.AuthenticationViewModel
import com.github.phajduk.rxvalidator.RxValidator

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers



class LoginActivity : AppCompatActivity(), IDataManager.OnLoginListener {

    private val MIKKI_LOGIN = "MikkiLogin"
    val viewModel = AuthenticationViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        createInputRxValidator()

        btn_login.setOnClickListener {
            getUserInput()
        }


        fab.setOnClickListener { view ->
            val manager = FingerprintManagerCompat.from(this)

            if (manager.isHardwareDetected && manager.hasEnrolledFingerprints()) {
                showFingerprintAuth()
            } else {
                Snackbar.make(view, "Fingerprint authentication is not supported.", Snackbar.LENGTH_SHORT).show()
            }

        }


    }
    private fun createInputRxValidator(){
        RxValidator.createFor(et_login_email)
                .nonEmpty()
                .email()
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.item.error = if (result.isProper) null else result.message
                    Log.i(MIKKI_LOGIN, "Validation result " + result.toString())
                }, { throwable -> Log.e(MIKKI_LOGIN, "Validation error", throwable) })

        RxValidator.createFor(et_login_pw)
                .nonEmpty()
                .with(NonEmptyValidator())
                .minLength(3, "Min length is 2")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.item.error = if (result.isProper) null else result.message
                    Log.i(MIKKI_LOGIN, "Validation result " + result.toString())
                }, { throwable -> Log.e(MIKKI_LOGIN, "Validation error", throwable) })
    }

    private fun getUserInput() {

        var email = et_login_email.text.toString()
        var password = et_login_pw.text.toString()
        Log.d(MIKKI_LOGIN, "email: $email, password: $password")
        val loginInfo = LoginInfo(email = email, password = password)
        viewModel.login(this, loginInfo)
    }

    private fun showFingerprintAuth() {
        val dialog = FingerprintDialog.newInstance(
                "Sign In",
                "Confirm fingerprint to continue."
        )
        dialog.show(supportFragmentManager, FingerprintDialog.FRAGMENT_TAG)
    }

    override fun getUserInfo(userInfo: LoginUserInfo) {
        toast("Login Success")
        val bundle = Bundle()
        bundle.putParcelable("userInfo", userInfo)
        val role = userInfo.userrole

        Log.d(MIKKI_LOGIN, userInfo.toString())

        if (BuildConfig.FLAVOR.equals("manager")) {
            Log.d(MIKKI_LOGIN, "check flavors.................")
            if(role =="admin"){
                intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent, bundle)
            }else{
                toast("This is not a Manager Account, please log in with manager email")
            }

        } else if (BuildConfig.FLAVOR.equals("developer")) {
            if(role == "user"){
                intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent, bundle)
            }else{
                toast("This is not a Developer Account, please log in with developer email")
            }
        }
    }

    override fun errorMsg(msg: String) {
        var errorMsg = msg

        if(msg == "0"){
            errorMsg = "Reached max login attempts, " +
                    "account will be locked for 5 minutes"

        }else{
            errorMsg = "Incorrect Password! Attempt Left: $msg"
        }

        tv_login_error_msg.text = errorMsg
    }
}
