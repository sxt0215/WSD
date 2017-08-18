package com.jyph.wsdapp.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.WindowCompat;
import android.view.View;
import android.view.ViewStub;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jyph.wsdapp.R;
import com.jyph.wsdapp.basemvp.view.base.BaseActivity;
import com.jyph.wsdapp.common.network.ApiSettings;
import com.jyph.wsdapp.common.utils.LogMe;
import com.jyph.wsdapp.mvp.presenter.WebViewPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sxt on 16/12/20.
 */
public class WebViewActivity extends BaseActivity<WebViewPresenter> {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.myProgressBar)
    ProgressBar myProgressBar;
    @BindView(R.id.error_view_stub)
    ViewStub errorViewStub;
    @BindView(R.id.web_container)
    LinearLayout webContainer;
    private String title;
    private int mode;
    private String strUrl;
    private String token;
    private String strContent;

    public final static int STRINGCONTENT = 1;
    public final static int WEB_URL = 2;
    public final static int WEB_H5_URL = 3;
    public final static int CMS_URL = 4; //default: Protocol
    public final static int CMS_URL_PROTOCOL = 5;
    public final static int CMS_URL_HELP = 6;
    private WebView webview;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initView();
        getIntents();
        if (mode == STRINGCONTENT)
            webViewInitialByContent(strContent);
        else if (mode >= CMS_URL)// cms url
            getCMS(mode, strUrl);
        else if (mode == WEB_URL)//web
            webViewInitialByWebUrl(strUrl, token);
    }

    private void initView() {
        imgLeft.setImageResource(R.drawable.back);
        tvTitle.setText("用户协议");


    }

    public void getCMS(int category, String url) {
        switch (category) {
            case WebViewActivity.CMS_URL_PROTOCOL:
//                getPresenter().getProtocol(url);
                break;
            default:
//                getPresenter().getHelpCenter(url);
                break;
        }
    }

    public void webViewInitialByWebUrl(String weburl, String token) {//银行
        webview.getSettings().setJavaScriptEnabled(true); // 设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setSavePassword(false);
        webview.getSettings().setSaveFormData(false);
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new MyWebChromeClient());
        CookieManager.getInstance().setCookie(weburl, token);
        webview.loadUrl(weburl);
        LogMe.d("Webview", "url:" + weburl);
    }

    public void webViewInitialByContent(String content) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setDefaultFontSize(18);
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new MyWebChromeClient());
        webview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    @Override
    public WebViewPresenter bindPresenter() {
        return new WebViewPresenter(this);
    }

    private void getIntents() {
        Intent intent = this.getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
            mode = intent.getIntExtra("mode", 0);
            strUrl = intent.getStringExtra("url");
            strContent = intent.getStringExtra("content");
            token = intent.getStringExtra("token");
        }
    }

    public static void startAc(Context context, String title, int mode,
                               String url, String content, String token) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("mode", mode);
        intent.putExtra("url", url);
        intent.putExtra("token", token);
        intent.putExtra("content", content);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        LogMe.e("webview", "\ntitle:" + title + "\nmode:" + mode + "\nurl:" + url + "\ncontent:" + content + "\ntoken:" + token);
    }

    @OnClick(R.id.img_left)
    public void onViewClicked() {
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        // 在WebView中而不是默认浏览器中显示页面
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			view.loadUrl(url);
//			return true;
// 			"https://lmjf.uats.cc/api/v2/"
            if (url.contains(ApiSettings.URL_BASE)) {
                finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        // @Override
        // public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // super.onPageStarted(view, url, favicon);
        // }
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            //这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
            //view.loadUrl(file:///android_asset/error.html );
//            showErrorLayout("");
        }

        //support ssl
        //http://stackoverflow.com/questions/5977977/does-the-web-view-on-android-support-ssl
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

//    /**
//     * show error layout when request error
//     *
//     * @param errorMsg
//     */
//    private void showErrorLayout(String errorMsg) {
//
//        if (errorViewStub.isInflated()) return;
//        errorViewStub.getViewStub().inflate();
//        webview.setVisibility(View.INVISIBLE);
//    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                myProgressBar.setVisibility(View.GONE);
            } else {
                if (View.GONE == myProgressBar.getVisibility()) {
                    myProgressBar.setVisibility(View.VISIBLE);
                }
                myProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
