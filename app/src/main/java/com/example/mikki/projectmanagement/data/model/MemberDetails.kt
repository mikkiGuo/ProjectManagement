package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MemberDetails(

		@field:SerializedName("userlastname")
		val userlastname: String? = null,

		@field:SerializedName("userfirstname")
		val userfirstname: String? = null,

		@field:SerializedName("usermobile")
		val usermobile: String? = null,

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
			source.readString()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeString(userlastname)
		writeString(userfirstname)
		writeString(usermobile)
		writeString(userid)
		writeString(useremail)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<MemberDetails> = object : Parcelable.Creator<MemberDetails> {
			override fun createFromParcel(source: Parcel): MemberDetails = MemberDetails(source)
			override fun newArray(size: Int): Array<MemberDetails?> = arrayOfNulls(size)
		}
	}
}