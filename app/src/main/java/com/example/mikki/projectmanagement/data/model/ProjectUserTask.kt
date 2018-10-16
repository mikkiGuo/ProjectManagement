package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class ProjectUserTask(

	@field:SerializedName("view tasks")
	val projectUserTasks: List<ProjectUserTasksItem?>? = null
)