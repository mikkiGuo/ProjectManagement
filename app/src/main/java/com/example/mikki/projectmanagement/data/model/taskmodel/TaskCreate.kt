package com.example.mikki.projectmanagement.data.model.taskmodel

import com.google.gson.annotations.SerializedName

data class TaskCreate(

	@field:SerializedName("msg")
	var msg: List<String?>? = null,

	@field:SerializedName("project_id")
	var projectId: String? = null,

	@field:SerializedName("task_id")
	var taskId: Int? = null
) {
	override fun toString(): String {
		return "TaskCreate(" +
				"msg=$msg," +
				" projectId=$projectId," +
				" taskId=$taskId)"
	}
}