package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable

data class CreateTeam (
        var projectId:String,
        var userId:String,
        var position : String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(projectId)
        parcel.writeString(userId)
        parcel.writeString(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreateTeam> {
        override fun createFromParcel(parcel: Parcel): CreateTeam {
            return CreateTeam(parcel)
        }

        override fun newArray(size: Int): Array<CreateTeam?> {
            return arrayOfNulls(size)
        }
    }
}