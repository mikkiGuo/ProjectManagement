package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Login(

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
		writeString(msg)
		writeString(userlastname)
		writeString(userfirstname)
		writeString(appapikey)
		writeString(userrole)
		writeString(userid)
		writeString(useremail)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Login> = object : Parcelable.Creator<Login> {
			override fun createFromParcel(source: Parcel): Login = Login(source)
			override fun newArray(size: Int): Array<Login?> = arrayOfNulls(size)
		}
	}
}