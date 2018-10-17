package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TaskMemberList(

		@field:SerializedName("members")
		var members: ArrayList<TaskMemberItem>? = null

) : Parcelable {

	override fun toString(): String {
		return "TaskMemberList(members=$members)"
	}

	constructor(source: Parcel) : this(
			source.createTypedArrayList(TaskMemberItem.CREATOR)
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeTypedList(members)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<TaskMemberList> = object : Parcelable.Creator<TaskMemberList> {
			override fun createFromParcel(source: Parcel): TaskMemberList = TaskMemberList(source)
			override fun newArray(size: Int): Array<TaskMemberList?> = arrayOfNulls(size)
		}
	}
}