package com.example.mikki.projectmanagement.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.Register
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), IDataManager.OnRegisterListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

    override fun isRegistered(boolean: Boolean) {
        Log.d("ninntag", boolean.toString())
        if (boolean) {
            toast("Successfully Registered")
        }
        else {
            toast("Registration Failed. User with that email already exists.")
        }
    }
}
