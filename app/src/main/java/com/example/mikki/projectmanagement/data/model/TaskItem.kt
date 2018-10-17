package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TaskItem(

	@field:SerializedName("taskstatus")
	var taskstatus: String? = null,

	@field:SerializedName("taskdesc")
	var taskdesc: String? = null,

	@field:SerializedName("endstart")
	var endstart: String? = null,

	@field:SerializedName("taskname")
	var taskname: String? = null,

	@field:SerializedName("startdate")
	var startdate: String? = null,

	@field:SerializedName("projectid")
	var projectid: String? = null,

	@field:SerializedName("taskid")
	var taskid: String? = null,

	@field:SerializedName("userid")
	var userid: String? = null

): Parcelable {
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
		return "TaskItem(" +
				"taskstatus=$taskstatus," +
				" taskdesc=$taskdesc," +
				" endstart=$endstart," +
				" taskname=$taskname," +
				" startdate=$startdate," +
				" projectid=$projectid," +
				" taskid=$taskid," +
				" userid=$userid)"
	}


	companion object CREATOR : Parcelable.Creator<TaskItem> {
		override fun createFromParcel(parcel: Parcel): TaskItem {
			return TaskItem(parcel)
		}

		override fun newArray(size: Int): Array<TaskItem?> {
			return arrayOfNulls(size)
		}
	}


}