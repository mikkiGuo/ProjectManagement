package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LoginUserInfo(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("userlastname")
	val userlastname: String? = null,

	@field:SerializedName("userfirstname")
	val userfirstname: String? = null,

	@field:SerializedName("appapikey ")
	val appapikey: String? = null,

	@field:SerializedName("userrole")
	val userrole: String? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("useremail")
	val useremail: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(msg)
		parcel.writeString(userlastname)
		parcel.writeString(userfirstname)
		parcel.writeString(appapikey)
		parcel.writeString(userrole)
		parcel.writeString(userid)
		parcel.writeString(useremail)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<LoginUserInfo> {
		override fun createFromParcel(parcel: Parcel): LoginUserInfo {
			return LoginUserInfo(parcel)
		}

		override fun newArray(size: Int): Array<LoginUserInfo?> {
			return arrayOfNulls(size)
		}
	}
}