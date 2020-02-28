package au.com.swivelgroup.newsapp.ui.main.tabs.userpref

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.app.App
import au.com.swivelgroup.newsapp.base.BaseFragment
import au.com.swivelgroup.newsapp.data.remote.TopNewsHeadlinesResponse
import au.com.swivelgroup.newsapp.ui.main.tabs.topnews.TopNewsHeadlinesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_top_news_headlines.view.layout_title
import kotlinx.android.synthetic.main.fragment_top_news_headlines.view.ll_no_results_found
import kotlinx.android.synthetic.main.fragment_top_news_headlines.view.progress
import kotlinx.android.synthetic.main.fragment_top_news_headlines.view.rv_hot_news_headlines
import kotlinx.android.synthetic.main.fragment_top_news_headlines.view.srl_hot_news_headlines
import kotlinx.android.synthetic.main.fragment_user_preference_news.view.*
import kotlinx.android.synthetic.main.top_tool_bar_main.view.*
import timber.log.Timber
import javax.inject.Inject

class UserPreferenceNewsFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var preferenceNewsVM: PreferenceNewsVM

    companion object {
        lateinit var view1: View

        var newsResponse: TopNewsHeadlinesResponse? = null
    }

    private lateinit var mRunnable: Runnable
    private lateinit var mHandler: Handler

    private var keySearch: String = "bitcoin"

    private val newsCategoryList = arrayOf("bitcoin", "apple", "earthquake", "animal")

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_search_news -> {

                val builder = AlertDialog.Builder(view1.context)
                builder.setItems(newsCategoryList) { dialog, which ->
                    when (which) {
                        0 -> {
                            keySearch = "bitcoin"
                            fetchUserPreferenceNews("bitcoin")
                            view1.layout_title.tv_title_top.text = "Preference News - BITCOIN"
                        }
                        1 -> {
                            keySearch = "apple"
                            fetchUserPreferenceNews("apple")
                            view1.layout_title.tv_title_top.text = "Preference News - APPLE"
                        }
                        2 -> {
                            keySearch = "earthquake"
                            fetchUserPreferenceNews("earthquake")
                            view1.layout_title.tv_title_top.text = "Preference News - EARTHQUAKE"
                        }
                        3 -> {
                            keySearch = "animal"
                            fetchUserPreferenceNews("animal")
                            view1.layout_title.tv_title_top.text = "Preference News - ANIMAL"
                        }
                    }
                }
                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        // Initialize the handler instance
        mHandler = Handler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_user_preference_news, container, false)
        view1 = view

        view1.layout_title.tv_title_top.text = "Preference News - BITCOIN"

        view1.ll_search_news.setOnClickListener(this)

        view1.srl_hot_news_headlines.setOnRefreshListener {
            // Initialize a new Runnable
            mRunnable = Runnable {

                // Hide swipe to refresh icon animation
                view1.srl_hot_news_headlines.isRefreshing = false

                fetchUserPreferenceNews(keySearch)
            }

            // Execute the task after specified time
            mHandler.postDelayed(
                mRunnable,
                500
            )
        }

        return view1
    }

    /**
     * fetch user preference news
     */
    private fun fetchUserPreferenceNews(key: String) {

        subscription.add(
            preferenceNewsVM.getPreferenceNewsData(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress(view1.progress, true) }
                .doOnTerminate { showProgress(view1.progress, false) }
                .subscribe(
                    {
                        if (it != null && preferenceNewsVM.isSuccessResponse) {

                            if (preferenceNewsVM.dataResponse != null && preferenceNewsVM.dataResponse?.articles != null && preferenceNewsVM.dataResponse?.articles!!.isNotEmpty()) {

                                App.topNewsHeadlinesResponse = preferenceNewsVM.dataResponse
                                newsResponse = preferenceNewsVM.dataResponse!!


                                Timber.d("news list success")

                                // show pokemon items in recycler view
                                view1.ll_no_results_found?.visibility = View.GONE

                                var adapter = newsResponse!!.articles?.let { it1 ->
                                    TopNewsHeadlinesAdapter(
                                        it1
                                    )
                                }

                                populateListViewData(adapter)

                            } else {

                                populateListViewData(null)

                                view1.ll_no_results_found?.visibility = View.VISIBLE
                            }

                        } else {
//                            handleUnsuccessfulError(it)
                        }
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    /**
     * populate data in recycle view
     *
     * @param adapter
     */
    private fun populateListViewData(adapter: TopNewsHeadlinesAdapter?) {
        view1.rv_hot_news_headlines?.adapter = adapter

        view1?.rv_hot_news_headlines?.adapter?.notifyDataSetChanged()

        view1?.rv_hot_news_headlines?.layoutManager =
            LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()

        fetchUserPreferenceNews(keySearch)
    }
}
