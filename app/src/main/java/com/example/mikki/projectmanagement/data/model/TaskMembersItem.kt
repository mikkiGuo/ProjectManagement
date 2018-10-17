package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class TaskMembersItem(

	@field:SerializedName("projectid")
	val projectid: String? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("assignid")
	val assignid: String? = null,

	@field:SerializedName("taskid")
	val taskid: String? = null
)