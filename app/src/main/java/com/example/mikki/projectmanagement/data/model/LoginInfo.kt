package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable

data class LoginInfo (
        var email:String,
        var password:String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginInfo> {
        override fun createFromParcel(parcel: Parcel): LoginInfo {
            return LoginInfo(parcel)
        }

        override fun newArray(size: Int): Array<LoginInfo?> {
            return arrayOfNulls(size)
        }
    }
}