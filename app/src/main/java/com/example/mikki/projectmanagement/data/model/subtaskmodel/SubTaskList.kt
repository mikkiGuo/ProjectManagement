package com.example.mikki.projectmanagement.data.model.subtaskmodel


import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.google.gson.annotations.SerializedName


data class SubTaskList(

	@field:SerializedName("project sub task")
	val projectSubTask: List<ProjectSubTaskItem?>? = null
)