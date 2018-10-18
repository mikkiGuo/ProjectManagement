package com.example.mikki.projectmanagement.data.model

import com.google.gson.annotations.SerializedName


data class TeamMemberDetail(

	@field:SerializedName("userlastname")
	val userlastname: String? = null,

	@field:SerializedName("userfirstname")
	val userfirstname: String? = null,

	@field:SerializedName("usermobile")
	val usermobile: String? = null,

	@field:SerializedName("userid")
	val userid: String? = null,

	@field:SerializedName("useremail")
	val useremail: String? = null
)