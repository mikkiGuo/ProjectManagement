package com.example.mikki.projectmanagement.data.model.taskmodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TaskList(

	@field:SerializedName("project task")
	var task: ArrayList<TaskItem>? = null

): Parcelable {
	constructor(parcel: Parcel) : this() {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {

	}

	override fun describeContents(): Int {
		return 0
	}

	override fun toString(): String {
		return "TaskList(task=$task)"
	}

	companion object CREATOR : Parcelable.Creator<TaskList> {
		override fun createFromParcel(parcel: Parcel): TaskList {
			return TaskList(parcel)
		}

		override fun newArray(size: Int): Array<TaskList?> {
			return arrayOfNulls(size)
		}
	}



}
