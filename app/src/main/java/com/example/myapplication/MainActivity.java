package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDataReceive();

    }

    public void appCall(View view) {


        // manifests => <data android:scheme="sample" android:host=" sampleurldata "/>


        // keyValue = ?[key]=[value]&[key]=[value]";
        // 호출한 App 데이터 보내기
        String keyValue ="?post_id=10&user=data";



        // url = "[schemeDataName]://[hostDataName]" 다른 앱 호출 할때 Data
        // scheme = 호출할 App scheme="[sampleDataName]"
        // host = 호출할 App host="[hostDataName]"
        // 사용 법 = String url = [schemeDataName]://[hostDataName]?[key]=[value]&[key]=[value]
        String uri = "sampletest://sampleurldata"+keyValue;


        // Url 방식으로 App 호출 하는 방법
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivityForResult(intent,0);
    }

    public void appDataReceive(){

        // 1.App에서 호출한 App 에서 데이터 받는방법

        // Intent intent = getIntent(); = 불러온 App 데이터 가져오기
        Intent intent = getIntent();
        // Intent.ACTION_VIEW.equals(intent.getAction()) = 호출 하는 방식 맞는지 확인
        if (Intent.ACTION_VIEW.equals(intent.getAction())){
            // Url url = intent.getData() = [schemeDataName]://[hostDataName]?[key]=[value]&[key]=[value] => 데이터가져옵니다
            Uri uri = intent.getData();
            // uri.getQueryParameter("[key값]") => 키값으로 데이터 가져오기 ;
            // 호출받은 App 보낸 key 값
            String text = uri.getQueryParameter("text");

            Log.i("TAG","My App : "+text);

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}
