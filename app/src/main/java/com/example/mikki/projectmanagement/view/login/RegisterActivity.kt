package com.example.mikki.projectmanagement.view.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.Register
import kotlinx.android.synthetic.main.content_register.*
import org.jetbrains.anko.intentFor
import com.example.mikki.projectmanagement.utils.validator.CustomNameValidator
import com.example.mikki.projectmanagement.utils.validator.NonEmptyValidator
import com.github.phajduk.rxvalidator.RxValidator
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_login.*
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers

class RegisterActivity : AppCompatActivity(), IDataManager.OnRegisterListener {
    private val MIKKI_REGISTER = "MikkiLogin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        createInputRxValidator()
        bt_register.setOnClickListener {
            var register = Register(
                    fname = et_registerFname.text.toString(),
                    lname = et_registerLname.text.toString(),
                    email = et_registerEmail.text.toString(),
                    pass = et_registerPass.text.toString(),
                    compSize = et_registerCompSize.text.toString(),
                    role = et_registerRole.text.toString(),
                    mobile = et_registerMobile.text.toString()
            )

            Log.d("ninntag", "RegisterActivity: " + register.toString())

            val iDataManager:IDataManager = DataManager()
            iDataManager.register(this, register)
        }
    }

    private fun createInputRxValidator(){
        RxValidator.createFor(et_registerEmail)
                .nonEmpty()
                .email()
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.item.error = if (result.isProper) null else result.message
                    Log.i(MIKKI_REGISTER, "Validation result " + result.toString())
                }, { throwable -> Log.e(MIKKI_REGISTER, "Validation error", throwable) })

        RxValidator.createFor(et_registerFname)
                .nonEmpty()
                .with(CustomNameValidator())
                .minLength(3, "Min length is 2")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.item.error = if (result.isProper) null else result.message
                    Log.i(MIKKI_REGISTER, "Validation result " + result.toString())
                }, { throwable -> Log.e(MIKKI_REGISTER, "Validation error", throwable) })

        RxValidator.createFor(et_registerLname)
                .nonEmpty()
                .with(CustomNameValidator())
                .minLength(3, "Min length is 2")
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    result.item.error = if (result.isProper) null else result.message
                    Log.i(MIKKI_REGISTER, "Validation result " + result.toString())
                }, { throwable -> Log.e(MIKKI_REGISTER, "Validation error", throwable) })


    }

    override fun isRegistered(boolean: Boolean) {
        Log.d("ninntag", boolean.toString())
        if (boolean) {
            toast("Successfully Registered")
            finish()
        }
        else {
            toast("Registration Failed. User with that email already exists.")
        }
    }
}
