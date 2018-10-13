package com.example.mikki.projectmanagement.data.model


import com.google.gson.annotations.SerializedName

data class SuccessMsg(

	@field:SerializedName("msg")
	val msg: List<String?>? = null
)