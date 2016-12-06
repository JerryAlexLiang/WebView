package com.liangyang.webview01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private EditText mUrlAdress;
    private Button mSerachBtn;
    private String webUrl;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        mWebView = (WebView) findViewById(R.id.webView);
        mUrlAdress = (EditText) findViewById(R.id.url_address);
        mSerachBtn = (Button) findViewById(R.id.click_btn);
        //去掉滚动条
        mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        //监听Button点击事件
        mSerachBtn.setOnClickListener(this);
        //启用支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true);

        /**
         * WebViewClient
         * 在点击请求的是链接是才会调用，shouldOverrideUrlLoading
         * 重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
         * 这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，
         * 取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的
         */
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }

    /**
     * Button的点击监听事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        //格式化URL
        webUrl = mUrlAdress.getEditableText().toString();
        System.out.println("====="+webUrl);
        if (TextUtils.isEmpty(webUrl)){
            return;
        }
        mWebView.loadUrl(webUrl);
    }

    /**
     * 如果希望浏览的网页后退而不是退出浏览器，需要WebView覆盖URL加载，
     * 让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问过的站点。
     * @param keyCode
     * @param event
     * @return
     */
    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mWebView.canGoBack()){
                mWebView.goBack();//返回上一层
                return true;
            }else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
