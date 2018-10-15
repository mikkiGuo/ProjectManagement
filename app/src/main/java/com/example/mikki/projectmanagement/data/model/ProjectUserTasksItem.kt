package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class ProjectUserTasksItem(

	@field:SerializedName("subtaskid")
	val subtaskid: String? = null,

	@field:SerializedName("projectid")
	val projectid: String? = null,

	@field:SerializedName("taskid")
	val taskid: String? = null
)