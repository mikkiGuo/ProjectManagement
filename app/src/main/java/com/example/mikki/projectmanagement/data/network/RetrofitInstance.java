package com.example.mikki.projectmanagement.data.network;

import com.example.mikki.projectmanagement.data.IDataManager;
import com.example.mikki.projectmanagement.data.model.Register;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    static Retrofit retrofit;
    final static String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
