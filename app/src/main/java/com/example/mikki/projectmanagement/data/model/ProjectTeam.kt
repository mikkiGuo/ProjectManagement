package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName


data class ProjectTeam(

	@field:SerializedName("projectteam")
	val projectteam: List<ProjectteamItem?>? = null
)