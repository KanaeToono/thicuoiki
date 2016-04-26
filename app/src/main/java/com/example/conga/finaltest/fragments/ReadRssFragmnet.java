package com.example.conga.finaltest.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.conga.finaltest.R;

/**
 * Created by ConGa on 26/04/2016.
 */
public class ReadRssFragmnet extends Fragment {

    private static String TAG = ReadRssFragmnet.class.getSimpleName();
    private WebView webView;
    private ProgressDialog mProgressDialog;
    private String linkTag;
    public static Activity mActivity;
    public static Context mContext;
    private Button button;
    String result;
    private View mCustomView;
    private int mOriginalSystemUiVisibility;
    private int mOriginalOrientation;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    protected FrameLayout mFullscreenContainer;
    private Handler mHandler;
    private  String  link;
    private int mPosition;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on ctreate Read webpage");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.readrssitemlayout, container, false);
        link = getArguments().getString("link");

    //    text = (HtmlTextView) view.findViewById(R.id.html_text);
        // text.setRemoveFromHtmlSpace(true);
      //  getActivity().setTitle(item.getTitle());
        //  mFloatingActionButton = (FloatingActionButton) findViewById(R.id.overview_floating_action_button);
        // button = (Button) findViewById(R.id.btn_ok);
       // link = item.getLink();
//        linkTag =item.getLinkTag();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new SaveContentRssAsyncTask().execute();
//            }
//        });
        webView = (WebView) view.findViewById(R.id.webView);
        setUpWebViewDefaults(webView);
//        mContext = getApplicationContext();
//        mActivity = ReadRssActivity.this;

//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setScrollbarFadingEnabled(false);
//        webView.setScrollBarStyle(webView.SCROLLBARS_OUTSIDE_OVERLAY);
//        webView.setInitialScale(1);
//        webView.getSettings().setLightTouchEnabled(true);
//        webView.getSettings().setSupportMultipleWindows(true);
//     //   webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//      //  webView.setWebViewClient(new MyWebViewClient());
//        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
//            webView.getSettings().setDisplayZoomControls(false);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(link);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            //
//            @Override
//            public Bitmap getDefaultVideoPoster() {
//
//                return BitmapFactory.decodeResource(getApplicationContext().getResources(),
//                        R.drawable.video_poster);
//            }

            @Override
            public void onShowCustomView(View view,
                                         WebChromeClient.CustomViewCallback callback) {
                // if a view already exists then immediately terminate the new one
                if (mCustomView != null) {
                    onHideCustomView();
                    return;
                }

                // 1. Stash the current state
                mCustomView = view;
                mOriginalSystemUiVisibility = getActivity().getWindow().getDecorView().getSystemUiVisibility();
                mOriginalOrientation = getActivity().getRequestedOrientation();

                // 2. Stash the custom view callback
                mCustomViewCallback = callback;

                // 3. Add the custom view to the view hierarchy
                FrameLayout decor = (FrameLayout)getActivity().getWindow().getDecorView();
                decor.addView(mCustomView, new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));


                // 4. Change the state of the window
                getActivity().getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_IMMERSIVE);
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            @Override
            public void onHideCustomView() {
                // 1. Remove the custom view
                FrameLayout decor = (FrameLayout)getActivity().getWindow().getDecorView();
                decor.removeView(mCustomView);
                mCustomView = null;

                // 2. Restore the state to it's original form
                getActivity().getWindow().getDecorView()
                        .setSystemUiVisibility(mOriginalSystemUiVisibility);
                getActivity().setRequestedOrientation(mOriginalOrientation);

                // 3. Call the custom view callback
                mCustomViewCallback.onCustomViewHidden();
                mCustomViewCallback = null;

            }



            //
//permission request API in WebChromeClient:
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d(TAG, "onPermissionRequest");
                getActivity().runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {
                        if (request.getOrigin().toString().equals(link)) {
                            request.grant(request.getResources());
                        } else {
                            request.deny();
                        }
                    }
                });
            }

        });


        return view;
    }

    private void setUpWebViewDefaults(WebView webView) {
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        WebSettings settings = webView.getSettings();
        webView.getSettings().setSupportZoom(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);
        settings.setSupportMultipleWindows(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(new WebViewClient());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie( true);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            mProgressDialog = ProgressDialog.show(getActivity(), "", "loading");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            Log.d("finish", url);
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        private static final String APP_SCHEME = "example-app:";

//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if (url.startsWith(APP_SCHEME)) {
//            String urlData=null;
//            try {
//                urlData = URLDecoder.decode(url.substring(APP_SCHEME.length()), "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            respondToData(urlData);
//            return true;
//        }
//        return false;
//    }


//    }
//
//    private class MyWebChromeClient extends WebChromeClient {
//
//        @Override
//        public void onProgressChanged(WebView view, int newProgress) {
//            super.onProgressChanged(view, newProgress);
//          //  mProgressDialog = ProgressDialog.show(ReadRssActivity.this, "" ,"loading");
////            mActivity.setProgress(newProgress * 1000);
////
//            if (newProgress == 100 && mProgressDialog.isShowing()) {
//                mProgressDialog.dismiss();
//            }
//        }
//
//        @Override
//        public boolean onJsAlert(WebView view, String url, String mes"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623sage, JsResult result) {
//
//            Log.d("finish", url);
//               return super.onJsAlert(view, url, message, result);
//
//        }
    }


    //xem lại trang đã xem trước đó
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // Check if the key event was the Back button and if there's history
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//           webView.goBack();
//            return true;
//        }
//        return super.getActivity().onKeyDown(keyCode, event);
//    }
}
