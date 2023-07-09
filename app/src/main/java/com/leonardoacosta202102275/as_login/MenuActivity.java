package com.leonardoacosta202102275.as_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MenuActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//    }
    private Button _tampilMahasiswaButton;
    private Intent _menuIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        _tampilMahasiswaButton = findViewById(R.id.TampilMahasiswaActivity);

        _tampilMahasiswaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TampilMahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//
//        _tampilMahasiswaButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AsyncHttpClient asyncHttpClient;
//                String url = "https://stmikpontianak.net/011100862/tampilMahasiswa.php";
//                asyncHttpClient = new AsyncHttpClient();
//                asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                        Toast.makeText(getApplicationContext(), "menu mahasiswa", Toast.LENGTH_SHORT).show();
//                        _menuIntent = new Intent(getApplicationContext(), TampilMahasiswaActivity.class);
//                        startActivity(_menuIntent);
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }
}