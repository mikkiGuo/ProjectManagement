package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName


data class ProjectList(

	@field:SerializedName("projects")
	val projects: List<ProjectsItem?>? = null
)