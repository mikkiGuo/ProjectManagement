package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName

data class ProjectsItem(

	@field:SerializedName("projectname")
	var projectname: String? = null,

	@field:SerializedName("endstart")
	var endstart: String? = null,

	@field:SerializedName("projectdesc")
	var projectdesc: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("startdate")
	var startdate: String? = null,

	@field:SerializedName("projectstatus")
	var projectstatus: String? = null
)