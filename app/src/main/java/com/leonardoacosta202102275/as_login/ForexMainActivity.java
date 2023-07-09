package com.leonardoacosta202102275.as_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class ForexMainActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView aoaTextView, hkdTextView, audTextView, awgTextView, aznTextView, btcTextView, bdtTextView, bgnTextView, bhdTextView, usdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        aoaTextView = (TextView) findViewById(R.id.aoaTextView);
        hkdTextView = (TextView) findViewById(R.id.hkdTextView);
        audTextView = (TextView) findViewById(R.id.audTextView);
        awgTextView = (TextView) findViewById(R.id.awgTextView);
        aznTextView = (TextView) findViewById(R.id.aznTextView);
        btcTextView = (TextView) findViewById(R.id.btcTextView);
        bdtTextView = (TextView) findViewById(R.id.bdtTextView);
        bgnTextView = (TextView) findViewById(R.id.bgnTextView);
        bhdTextView = (TextView) findViewById(R.id.bhdTextView);
        usdTextView = (TextView) findViewById(R.id.usdTextView);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);

        initSwipeRefreshLayout();
        initForex();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            initForex();

            swipeRefreshLayout.setRefreshing(false);
        });
    }
    public String formatNumber(double number, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }

    private void initForex() {
        loadingProgressBar.setVisibility(TextView.VISIBLE);
        String url = "https://openexchangerates.org/api/latest.json?app_id=f49c98f0d56a4555a5b9058f3ff1b91c";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                ForexRootModel rootModel = gson.fromJson(new String(responseBody), ForexRootModel.class);
                ForexRatesModel ratesModel = rootModel.getRatesModel();

                double aoa = ratesModel.getIDR() / ratesModel.getAOA();
                double hkd = ratesModel.getIDR() / ratesModel.getHKD();
                double aud = ratesModel.getIDR() / ratesModel.getAUD();
                double awg = ratesModel.getIDR() / ratesModel.getAWG();
                double azn = ratesModel.getIDR() / ratesModel.getAZN();
                double bam = ratesModel.getIDR() / ratesModel.getBTC();
                double bdt = ratesModel.getIDR() / ratesModel.getBDT();
                double bgn = ratesModel.getIDR() / ratesModel.getBGN();
                double bhd = ratesModel.getIDR() / ratesModel.getBHD();
                double idr = ratesModel.getIDR();

                aoaTextView.setText(formatNumber(aoa, "###,##0.##"));
                hkdTextView.setText(formatNumber(hkd, "###,##0.##"));
                audTextView.setText(formatNumber(aud, "###,##0.##"));
                awgTextView.setText(formatNumber(awg, "###,##0.##"));
                aznTextView.setText(formatNumber(azn, "###,##0.##"));
                btcTextView.setText(formatNumber(bam, "###,##0.##"));
                bdtTextView.setText(formatNumber(bdt, "###,##0.##"));
                bgnTextView.setText(formatNumber(bgn, "###,##0.##"));
                bhdTextView.setText(formatNumber(bhd, "###,##0.##"));
                usdTextView.setText(formatNumber(idr, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.VISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                loadingProgressBar.setVisibility(TextView.INVISIBLE);

            }
        });
    }
}