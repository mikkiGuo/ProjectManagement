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

) : Parcelable {

	constructor(source: Parcel) : this(
			source.readString(),
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
		writeString(taskstatus)
		writeString(taskdesc)
		writeString(endstart)
		writeString(taskname)
		writeString(startdate)
		writeString(projectid)
		writeString(taskid)
		writeString(userid)
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

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<TaskItem> = object : Parcelable.Creator<TaskItem> {
			override fun createFromParcel(source: Parcel): TaskItem = TaskItem(source)
			override fun newArray(size: Int): Array<TaskItem?> = arrayOfNulls(size)
		}
	}
}