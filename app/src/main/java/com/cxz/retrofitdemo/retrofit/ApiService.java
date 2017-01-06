package com.cxz.retrofitdemo.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * author: Shoxz.Cheng
 * Date: 2017-01-06
 * Time: 15:50
 */
public interface ApiService {

    /**
     * @GET注解就表示get请求，@Query表示请求参数，将会以key=value的方式拼接在url后面
     * @param id
     * @return
     */
    @GET("user/getUser")
    Call<User> getUser(@Query("id")String id);


    /**
     * 如果Query参数比较多，那么可以通过@QueryMap方式将所有的参数集成在一个Map统一传递
     * @param options
     * @return
     */
    @GET("user/getUser")
    Call<User> getUser(@QueryMap Map<String,String> options);


    /**
     * 假如你需要添加相同Key值，但是value却有多个的情况，一种方式是添加多个@Query参数，
     * 还有一种简便的方式是将所有的value放置在列表中，然后在同一个@Query下完成添加，实例代码如下：
     * @param name
     * @return
     */
    @GET("user/getUser")
    Call<User> getUser(@Query("q")List<String> name);

    /**
     * 如果请求的相对地址也是需要调用方传递，那么可以使用@Path注解
     * @param id
     * @return
     */
    @GET("user/getUser/{id}")
    Call<User> getUser(@Path("id")int id);

    /**
     * @FormUrlEncoded将会自动将请求参数的类型调整为application/x-www-form-urlencoded，
     * @Field注解将每一个请求参数都存放至请求体中，还可以添加encoded参数，该参数为boolean型
     * @param name
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("user/getUser")
    Call<String> getUser(@Field(value = "name",encoded = true)String name,@Field("id")String id);


    /**
     * 假如说有更多的请求参数，那么通过一个一个的参数传递就显得很麻烦而且容易出错，这个时候就可以用FieldMap
     * @param fields
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("user/getUser")
    Call<String> getUser(@FieldMap Map<String,String> fields, @Field("id")String id);


    /**
     * 如果Post请求参数有多个，那么统一封装到类中应该会更好，这样维护起来会非常方便
     * @param user
     * @return
     */
    @FormUrlEncoded
    @POST("user/getUser")
    Call<String> getUser(@Body User user);

}
