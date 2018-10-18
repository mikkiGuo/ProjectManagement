package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ProjectteamItem(

	@field:SerializedName("teammemberuserid")
	val teammemberuserid: String? = null,

	@field:SerializedName("subtaskid")
	val subtaskid: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("projectid")
	val projectid: String? = null,

	@field:SerializedName("taskid")
	val taskid: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(teammemberuserid)
		parcel.writeString(subtaskid)
		parcel.writeString(id)
		parcel.writeString(projectid)
		parcel.writeString(taskid)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ProjectteamItem> {
		override fun createFromParcel(parcel: Parcel): ProjectteamItem {
			return ProjectteamItem(parcel)
		}

		override fun newArray(size: Int): Array<ProjectteamItem?> {
			return arrayOfNulls(size)
		}
	}
}