package com.example.trt.rxjavademo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    Button button,button1;
    static FileDownLoadTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        task=new FileDownLoadTask();
        progressBar=findViewById(R.id.process);
        textView=findViewById(R.id.text);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.execute();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }
    public class FileDownLoadTask extends AsyncTask<Void,Integer,String>{

        @Override
        protected String  doInBackground(Void... voids) {
            int i=0;
            while (i<=100){
                i++;
                publishProgress(i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "已完成";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            textView.setText("已完成");
        }
    }

    @Override
    protected void onDestroy() {
        task.cancel(true);
        super.onDestroy();
    }
}
