package com.cxz.retrofitdemo.retrofit;

import retrofit2.Retrofit;

/**
 * author: Shoxz.Cheng
 * Date: 2017-01-06
 * Time: 15:57
 */
public class ServiceGenerator {


    public static <T> T createService(Class<T> service){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .build();

        return retrofit.create(service);

    }

}
