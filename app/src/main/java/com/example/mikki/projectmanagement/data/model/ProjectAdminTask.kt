package com.example.mikki.projectmanagement.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProjectAdminTask() : Parcelable {

	@field:SerializedName("project task")
	var projectAdminTask: ArrayList<ProjectAdminTaskItem>? = null

	constructor(parcel: Parcel) : this() {

	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {

	}

	override fun describeContents(): Int {
		return 0
	}

	override fun toString(): String {
		return "ProjectAdminTask(projectAdminTask=$projectAdminTask)"
	}

	companion object CREATOR : Parcelable.Creator<ProjectAdminTask> {
		override fun createFromParcel(parcel: Parcel): ProjectAdminTask {
			return ProjectAdminTask(parcel)
		}

		override fun newArray(size: Int): Array<ProjectAdminTask?> {
			return arrayOfNulls(size)
		}
	}
}
