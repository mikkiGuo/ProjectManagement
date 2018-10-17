package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName

data class CreateProjectSuccessMsg(

	@field:SerializedName("msg")
	val msg: List<String?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)