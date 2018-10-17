package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName

data class EmployeesItem(

	@field:SerializedName("empdesignation")
	val empdesignation: String? = null,

	@field:SerializedName("empid")
	val empid: String? = null,

	@field:SerializedName("empfirstname")
	var empfirstname: String? = null,

	@field:SerializedName("dateofjoining")
	val dateofjoining: String? = null,

	@field:SerializedName("emplastname")
	var emplastname: String? = null,

	@field:SerializedName("empemail")
	val empemail: String? = null,

	@field:SerializedName("empmobile")
	val empmobile: String? = null
)