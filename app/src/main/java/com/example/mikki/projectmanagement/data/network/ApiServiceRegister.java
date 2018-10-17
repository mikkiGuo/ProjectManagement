package com.example.mikki.projectmanagement.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceRegister {

    // pms_reg.php?first_name=aamir&last_name=husain&email=aa@aa.com&mobile=9876543210&password=12345678&company_size=500&your_role=TL
    @GET("pms_reg.php")
    Call<String> register(@Query("first_name") String fname,
                          @Query("last_name") String lname,
                          @Query("email") String email,
                          @Query("mobile") String mobile,
                          @Query("password") String pass,
                          @Query("company_size") String company_size,
                          @Query("your_role") String role);
}
