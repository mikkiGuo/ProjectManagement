package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.LoginInfo

class AuthenticationViewModel:BaseObservable() {

    val dataManager: IDataManager = DataManager()

    fun login(listener:IDataManager.OnLoginListener,loginInfo: LoginInfo){
        dataManager.login(listener, loginInfo)
    }

}