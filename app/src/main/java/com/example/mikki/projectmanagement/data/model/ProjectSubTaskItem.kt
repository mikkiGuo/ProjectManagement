package com.example.mikki.projectmanagement.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ProjectSubTaskItem(

	@field:SerializedName("subtaskdesc")
	var subtaskdesc: String? = null,

	@field:SerializedName("endstart")
	var endstart: String? = null,

	@field:SerializedName("subtaskname")
	var subtaskname: String? = null,

	@field:SerializedName("subtaskid")
	val subtaskid: String? = null,

	@field:SerializedName("subtaskstatus")
	var subtaskstatus: String? = null,

	@field:SerializedName("startdate")
	var startdate: String? = null,

	@field:SerializedName("projectid")
	var projectid: String? = null,

	@field:SerializedName("taskid")
	var taskid: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString(),
			parcel.readString()) {
	}

	override fun writeToParcel(dest: Parcel?, flags: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun describeContents(): Int {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	companion object CREATOR : Parcelable.Creator<ProjectSubTaskItem> {
		override fun createFromParcel(parcel: Parcel): ProjectSubTaskItem {
			return ProjectSubTaskItem(parcel)
		}

		override fun newArray(size: Int): Array<ProjectSubTaskItem?> {
			return arrayOfNulls(size)
		}
	}
}