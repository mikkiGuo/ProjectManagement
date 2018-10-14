package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName


data class SubTaskList(

	@field:SerializedName("project sub task")
	val projectSubTask: List<ProjectSubTaskItem?>? = null
)