package au.com.swivelgroup.newsapp.ui.main.tabs.topnews.details

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.base.BaseActivity
import au.com.swivelgroup.newsapp.data.remote.Article
import au.com.swivelgroup.newsapp.databinding.ActivityNewsDetailBinding
import au.com.swivelgroup.newsapp.ui.common.WebViewActivity
import au.com.swivelgroup.newsapp.util.DateTimeUtil
import com.bumptech.glide.Glide

class NewsDetailActivity : BaseActivity(), View.OnClickListener {

    companion object {
        var article: Article? = null
        var mBinding: ActivityNewsDetailBinding? = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_news -> {
                WebViewActivity.url_news = article?.url
                WebViewActivity.title = article?.source?.name
                startWebView()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)

        setSupportActionBar(mBinding?.toolbarDetails)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mBinding?.fabNews?.setOnClickListener(this)

        title = article?.source?.name

        populateNewsDetails()
    }

    /**
     * populate news details data
     */
    private fun populateNewsDetails() {
        mBinding?.layoutNewsDetails?.tvHeadlineTitle?.text = article?.title
        mBinding?.layoutNewsDetails?.tvNewsDescription?.text = article?.description
        mBinding?.layoutNewsDetails?.tvNewsContent?.text = article?.content
        mBinding?.layoutNewsDetails?.tvAuthor?.text = article?.author
        if (!article?.publishedAt.isNullOrEmpty()) {
            var dateDisplay = DateTimeUtil.getFullDateDisplay(article?.publishedAt)
            mBinding?.layoutNewsDetails?.tvPublishDate?.text = dateDisplay
        }

        if(article?.urlToImage != null && article?.urlToImage!!.isNotEmpty()) {
            Glide.with(this)
                .load(article?.urlToImage)
                .into(mBinding?.layoutNewsDetails?.ivNewsHeadline!!)
        } else {
            mBinding?.layoutNewsDetails?.ivNewsHeadline?.visibility = View.GONE
        }
    }


}
