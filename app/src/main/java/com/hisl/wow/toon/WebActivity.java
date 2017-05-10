package com.hisl.wow.toon;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progress;
    String toonurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view){
                Snackbar.make(view, "새로고침 버튼입니다", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                return true;
            }
        });
        toonurl = getIntent().getStringExtra("toon");
        webView = (WebView)findViewById(R.id.webView);
        progress = (ProgressBar) findViewById(R.id.progress);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();
        mAdView.loadAd(adRequest);

        WebSettings webSettings = webView.getSettings();
        webSettings.setSaveFormData(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebBrowserClient());
        webView.loadUrl(toonurl);


        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                progress.setProgress(newProgress);
            }
        });
    }

    final class WebBrowserClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  //url???덈줈 ?깅줉?섎㈃ ?밸럭濡?濡쒕뵫?섎뒗 遺遺?
            if (url.startsWith("kakaolink:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else if (url.startsWith("mailto:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {  // ?섏씠吏濡쒕뵫???쒖옉?섎㈃
            super.onPageStarted(view, url, favicon);
            progress.setVisibility(View.VISIBLE);  // ?꾨줈洹몃젅?ㅻ컮 蹂댁씠湲?
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress.setVisibility(View.INVISIBLE);
        }

        public void onReceivedError(WebView view, int errorCode,  // ?ㅻ쪟諛쒖깮??泥섎━遺遺?
                                    String description, String failingUri) {
            super.onReceivedError(view, errorCode, description, failingUri);
            switch (errorCode) {
                case ERROR_AUTHENTICATION:
                case ERROR_BAD_URL:
                case ERROR_CONNECT:
                case ERROR_FAILED_SSL_HANDSHAKE:
                case ERROR_FILE: //
                case ERROR_FILE_NOT_FOUND:
                case ERROR_HOST_LOOKUP:
                case ERROR_IO:
                case ERROR_PROXY_AUTHENTICATION:
                case ERROR_REDIRECT_LOOP:
                case ERROR_TIMEOUT:
                case ERROR_TOO_MANY_REQUESTS:
                case ERROR_UNKNOWN:
                case ERROR_UNSUPPORTED_AUTH_SCHEME:
                case ERROR_UNSUPPORTED_SCHEME:
                    Snackbar.make(view, "네트워크 또는 사이트에 문제가 잇는것 같습니다, 새로고침을 해보세요.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    webView.loadUrl("about:blank");
            }
        };

        public boolean onJsAlert(WebView view, String url, String message,
                                 JsResult result) {
            result.confirm();
            return true;
        }

        public void stopMediaPlayer() {
            MediaPlayer player = new MediaPlayer();
            if (player.isPlaying()) {
                player.stop();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStart() {
        super.onStart();
        CookieSyncManager.createInstance(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}