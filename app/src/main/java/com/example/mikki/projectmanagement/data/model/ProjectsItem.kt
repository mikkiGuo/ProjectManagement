package com.example.mikki.projectmanagement.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ProjectsItem(

	@field:SerializedName("projectname")
	var projectname: String? = null,

	@field:SerializedName("endstart")
	var endstart: String? = null,

	@field:SerializedName("projectdesc")
	var projectdesc: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("startdate")
	var startdate: String? = null,

	@field:SerializedName("projectstatus")
	var projectstatus: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(projectname)
		parcel.writeString(endstart)
		parcel.writeString(projectdesc)
		parcel.writeString(id)
		parcel.writeString(startdate)
		parcel.writeString(projectstatus)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ProjectsItem> {
		override fun createFromParcel(parcel: Parcel): ProjectsItem {
			return ProjectsItem(parcel)
		}

		override fun newArray(size: Int): Array<ProjectsItem?> {
			return arrayOfNulls(size)
		}
	}
}