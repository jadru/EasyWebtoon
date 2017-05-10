package com.hisl.wow.toon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    String b11 = "http://m.comic.naver.com/index.nhn#none";
    String b12 = "http://m.webtoon.daum.net/m/";
    String b13 = "http://m.comics.nate.com/main2/webtoon.php";
    String b14 = "https://webtoon.olleh.com/web/main.kt";
    String b15 = "http://m.tstore.co.kr/mobilepoc/webtoon/weekdayList.omp?PrePageNm=/comic";
    String b16 = "https://www.lezhin.com/";
    String b17 = "http://toptoon.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogCustom_Destructive);
                builder.setTitle("도움말");
                builder.setMessage("EasyWebtoon은 간단한 웹툰 사이트들을 깔끔한 디자인을 가진 단 하나의 앱으로 만날수 있게 개발되었습니다. 아래의 버튼을 이용해서 추가를 원하는 사이트 등과 관련하여 메일을 통해서 문의 주실 수 있습니다.");
                builder.setPositiveButton("메일 보내기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_EMAIL, "ygpark0922@gmail.com");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "EasyWebtoon과 관련하여 문의드립니다");
                        startActivity(Intent.createChooser(intent, "문의 보내기"));
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view, "도움말 버튼입니다", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                return true;
            }
        });
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();
        mAdView.loadAd(adRequest);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b7 = (Button)findViewById(R.id.b7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = getPackageManager().getLaunchIntentForPackage("com.nhn.android.webtoon");
                if (start == null) {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("toon", b11);
                    startActivity(intent);
                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.nhn.android.webtoon");
                    startActivity(intent);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = getPackageManager().getLaunchIntentForPackage("net.daum.android.webtoon");
                if (start == null) {
                    Intent startt = getPackageManager().getLaunchIntentForPackage("net.daum.android.webtoon19");
                    if (startt == null) {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("toon", b12);
                    startActivity(intent);
                    } else {
                        Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("net.daum.android.webtoon19");
                        startActivity(intent);
                    }
                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("net.daum.android.webtoon");
                    startActivity(intent);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = getPackageManager().getLaunchIntentForPackage("com.nate.android.cartoon");
                if (start == null) {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("toon", b13);
                    startActivity(intent);
                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.nate.android.cartoon");
                    startActivity(intent);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = getPackageManager().getLaunchIntentForPackage("com.olleh.olltoon");
                if (start == null) {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("toon", b14);
                    startActivity(intent);
                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.olleh.olltoon");
                    startActivity(intent);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, WebActivity.class);
                intent.putExtra("toon", b15);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = getPackageManager().getLaunchIntentForPackage("com.lezhin.comics");
                if (start == null) {
                    Intent startt = getPackageManager().getLaunchIntentForPackage("com.lezhin.comics.tstore");
                    if (startt == null) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra("toon", b16);
                        startActivity(intent);
                    } else {
                        Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.lezhin.comics.tstore");
                        startActivity(intent);
                    }
                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.lezhin.comics");
                    startActivity(intent);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = getPackageManager().getLaunchIntentForPackage("com.toptooncomics.topviewer.google");
                if (start == null) {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    intent.putExtra("toon", b17);
                    startActivity(intent);
                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.toptooncomics.topviewer.google");
                    startActivity(intent);
                }
            }
        });
    }
}
