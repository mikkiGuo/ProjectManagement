package com.example.mikki.projectmanagement.data.model.taskmodel

import android.os.Parcel
import android.os.Parcelable
import com.example.mikki.projectmanagement.data.model.MemberDetails
import com.google.gson.annotations.SerializedName

data class TaskMemberItem(

		@field:SerializedName("projectid")
		var projectid: String? = null,

		@field:SerializedName("userid")
		var userid: String? = null,

		@field:SerializedName("assignid")
		var assignid: String? = null,

		@field:SerializedName("taskid")
		var taskid: String? = null,

		@field:SerializedName("subtaskid")
		var subtaskid: String? = null,

		@field:SerializedName("memberdetails")
		var memberdetails: MemberDetails? = null

) : Parcelable {

	constructor(source: Parcel) : this(
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readParcelable<MemberDetails>(MemberDetails::class.java.classLoader)
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeString(projectid)
		writeString(userid)
		writeString(assignid)
		writeString(taskid)
		writeString(subtaskid)
		writeParcelable(memberdetails, 0)
	}

	override fun toString(): String {
		return "TaskMemberItem(" +
				"projectid=$projectid," +
				" userid=$userid," +
				" assignid=$assignid," +
				" taskid=$taskid," +
				" taskid=$subtaskid," +
				" memberdetails=$memberdetails)"
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<TaskMemberItem> = object : Parcelable.Creator<TaskMemberItem> {
			override fun createFromParcel(source: Parcel): TaskMemberItem = TaskMemberItem(source)
			override fun newArray(size: Int): Array<TaskMemberItem?> = arrayOfNulls(size)
		}
	}
}