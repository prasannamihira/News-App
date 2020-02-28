package au.com.swivelgroup.newsapp.ui.common

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityWebViewBinding

    companion object{
        var url_news: String? = null
        var title: String? = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)

        setSupportActionBar(mBinding?.toolbarWebview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = title

        mBinding?.webview?.settings?.javaScriptEnabled = true

        mBinding?.webview?.loadUrl(url_news)
    }
}
