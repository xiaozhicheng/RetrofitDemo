package com.cxz.retrofitdemo.retrofit.upload;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cxz.retrofitdemo.retrofit.ServiceGenerator;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: Shoxz.Cheng
 * Date: 2017-01-06
 * Time: 17:14
 */
public class UploadActivity extends Activity {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    public void upload(){
        Uri file1Uri = null ;// 从文件选择器或者摄像头中获取
        Uri file2Uri = null;

        // 创建上传的service实例
        FileUploadService service =
                ServiceGenerator.createService(FileUploadService.class);

        // 创建文件的part (photo, video, ...)
        MultipartBody.Part body1 = prepareFilePart("video", file1Uri);
        MultipartBody.Part body2 = prepareFilePart("thumbnail", file2Uri);

        // 添加其他的part
        RequestBody description = createPartFromString("hello, this is description speaking");

        Call<ResponseBody> call = service.uploadMultipleFiles(description,body1,body2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });

    }


    private RequestBody createPartFromString(String descriptionString){
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA),descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = null; //= FileUtils.getFile(this, fileUri);

        // 为file建立RequestBody实例
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        // MultipartBody.Part借助文件名完成最终的上传
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

}
