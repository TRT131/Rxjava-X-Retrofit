package com.example.trt.rxjavademo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetofitTestActivity extends AppCompatActivity{
    TextView textView1,textView2;
    Button button;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retofit_test);
        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendMessage(new Message());
            }
        });
        Log.i("mainThread", "onCreate: "+android.os.Process.myTid());
        io.reactivex.Observable.create(new ObservableOnSubscribe<ProductResult>() {
            @Override
            public void subscribe(ObservableEmitter<ProductResult> e) throws Exception {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://suggest.taobao.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GetRequest_Interface request=retrofit.create(GetRequest_Interface.class);
                Map<String,String> maps=new HashMap<>();
                maps.put("code","utf-8");
                maps.put("q","卫衣");
                Call<ProductResult> repos=request.listProduct((HashMap<String, String>) maps);
                ProductResult productResult=repos.execute().body();
                e.onNext(productResult);
//                Retrofit retrofit=new Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:5000/")
//                        .addConverterFactory(ScalarsConverterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                Map<String,Object> maps=new HashMap<>();
//                maps.put("cmd","want");
//                maps.put("fund_key",11234);
//                RequestBody file=RequestBody.create(MediaType.parse("application/octet-stream"),"这是一个文件");
//                MultipartBody.Part part=MultipartBody.Part.createFormData("file","file.txt",file);
//                Call<JsonBean> repos=request.postTest("main",254,part);
//                Log.i("repos", "subscribe: "+repos);
//                JsonBean productResult=repos.execute().body();
//                e.onNext(productResult);
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ProductResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductResult value) {
                Log.i("mainThread", "onNext: "+android.os.Process.myTid());
                Log.i("json", "onNext: "+value.toString());
                textView1.setText(value.getResult().get(0).get(0));
                textView2.setText(value.getResult().get(0).get(1));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
