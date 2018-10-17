package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName


data class EmployeeList(

	@field:SerializedName("employees")
	val employees: List<EmployeesItem?>? = null
)