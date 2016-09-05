package support.prime.cell.androidviewtogles.gles;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import support.prime.cell.androidviewtogles.R;

/**
 * Created by Michael on 8/26/2016.
 */
public class MainAct extends Activity {

    private GLSurfaceView mGLSurfaceView;
    private GLRenderable mGLLinearLayout;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     initViews();
    }

    private void initViews() {
        setContentView(R.layout.main_act);

        //ViewToGLRenderer viewToGlRenderer = new CubeGLRender(this);
        ViewToGLRenderer viewToGlRenderer = new VRRender(this);

        mGLSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);
        mGLLinearLayout = (GLRenderable) findViewById(R.id.gl_layout);
        mWebView = (WebView) findViewById(R.id.web_view);

        mGLSurfaceView.setEGLContextClientVersion(1);
        mGLSurfaceView.setRenderer(viewToGlRenderer);

        mGLLinearLayout.setViewToGLRenderer(viewToGlRenderer);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("http://www.google.com");
    }
}
