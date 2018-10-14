package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class ProjectTask(

	@field:SerializedName("project task")
	val projectTask: List<ProjectTaskItem?>? = null
)