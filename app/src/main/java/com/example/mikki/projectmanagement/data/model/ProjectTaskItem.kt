package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class ProjectTaskItem(

	@field:SerializedName("taskstatus")
	var taskstatus: String? = null,

	@field:SerializedName("taskdesc")
	var taskdesc: String? = null,

	@field:SerializedName("endstart")
	var endstart: String? = null,

	@field:SerializedName("taskname")
	var taskname: String? = null,

	@field:SerializedName("startdate")
	var startdate: String? = null,

	@field:SerializedName("projectid")
	var projectid: String? = null,

	@field:SerializedName("taskid")
	var taskid: String? = null
)