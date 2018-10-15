package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName


data class ProjectSubTaskItem(

	@field:SerializedName("subtaskdesc")
	var subtaskdesc: String? = null,

	@field:SerializedName("endstart")
	var endstart: String? = null,

	@field:SerializedName("subtaskname")
	var subtaskname: String? = null,

	@field:SerializedName("subtaskid")
	val subtaskid: String? = null,

	@field:SerializedName("subtaskstatus")
	var subtaskstatus: String? = null,

	@field:SerializedName("startdate")
	var startdate: String? = null,

	@field:SerializedName("projectid")
	var projectid: String? = null,

	@field:SerializedName("taskid")
	var taskid: String? = null
)