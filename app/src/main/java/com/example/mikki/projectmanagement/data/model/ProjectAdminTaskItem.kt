package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProjectAdminTaskItem() : Parcelable {

	@field:SerializedName("taskstatus")
	var taskstatus: String? = null

	@field:SerializedName("taskdesc")
	var taskdesc: String? = null

	@field:SerializedName("endstart")
	var endstart: String? = null

	@field:SerializedName("taskname")
	var taskname: String? = null

	@field:SerializedName("startdate")
	var startdate: String? = null

	@field:SerializedName("projectid")
	var projectid: String? = null

	@field:SerializedName("taskid")
	var taskid: String? = null

	constructor(parcel: Parcel) : this() {
		taskstatus = parcel.readString()
		taskdesc = parcel.readString()
		endstart = parcel.readString()
		taskname = parcel.readString()
		startdate = parcel.readString()
		projectid = parcel.readString()
		taskid = parcel.readString()
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(taskstatus)
		parcel.writeString(taskdesc)
		parcel.writeString(endstart)
		parcel.writeString(taskname)
		parcel.writeString(startdate)
		parcel.writeString(projectid)
		parcel.writeString(taskid)
	}

	override fun describeContents(): Int {
		return 0
	}

	override fun toString(): String {
		return "ProjectAdminTaskItem(" +
				"taskstatus=$taskstatus," +
				" taskdesc=$taskdesc," +
				" endstart=$endstart," +
				" taskname=$taskname," +
				" startdate=$startdate," +
				" projectid=$projectid," +
				" taskid=$taskid)"
	}


	companion object CREATOR : Parcelable.Creator<ProjectAdminTaskItem> {
		override fun createFromParcel(parcel: Parcel): ProjectAdminTaskItem {
			return ProjectAdminTaskItem(parcel)
		}

		override fun newArray(size: Int): Array<ProjectAdminTaskItem?> {
			return arrayOfNulls(size)
		}
	}
}