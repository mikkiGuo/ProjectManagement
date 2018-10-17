package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class TaskMemberList(

	@field:SerializedName("members")
	val members: List<TaskMembersItem?>? = null
)