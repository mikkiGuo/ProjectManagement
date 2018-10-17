package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable

data class Register(
        var fname: String,
        var lname: String,
        var email: String,
        var mobile: String,
        var pass: String,
        var compSize: String,
        var role: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(fname)
        writeString(lname)
        writeString(email)
        writeString(mobile)
        writeString(pass)
        writeString(compSize)
        writeString(role)
    }

    override fun toString(): String {
        return "Register(fname='$fname', lname='$lname', email='$email', mobile='$mobile', pass='$pass', compSize='$compSize', role='$role')"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Register> = object : Parcelable.Creator<Register> {
            override fun createFromParcel(source: Parcel): Register = Register(source)
            override fun newArray(size: Int): Array<Register?> = arrayOfNulls(size)
        }
    }
}