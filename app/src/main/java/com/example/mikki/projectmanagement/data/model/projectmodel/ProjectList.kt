package com.example.mikki.projectmanagement.data.model.projectmodel


import com.google.gson.annotations.SerializedName


data class ProjectList(

	@field:SerializedName("projects")
	val projects: List<ProjectsItem?>? = null
)