package au.com.swivelgroup.newsapp.ui.main.tabs.topnews

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.app.App
import au.com.swivelgroup.newsapp.base.BaseFragment
import au.com.swivelgroup.newsapp.data.remote.TopNewsHeadlinesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_top_news_headlines.view.*
import timber.log.Timber
import javax.inject.Inject

class TopNewsHeadlinesFragment : BaseFragment() {

    @Inject
    lateinit var topNewsHeadlinesVM: TopNewsHeadlinesVM

    companion object {
        lateinit var view1: View

        var topNewsHeadlinesResponse: TopNewsHeadlinesResponse? = null
    }

    private lateinit var mRunnable: Runnable
    private lateinit var mHandler: Handler

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
        var view =  inflater.inflate(R.layout.fragment_top_news_headlines, container, false)
        view1 = view

        view1.srl_hot_news_headlines.setOnRefreshListener {
            // Initialize a new Runnable
            mRunnable = Runnable {

                // Hide swipe to refresh icon animation
                view1.srl_hot_news_headlines.isRefreshing = false

                fetchHotNewsHeadlines()
            }

            // Execute the task after specified time
            mHandler.postDelayed(
                mRunnable,
                500
            )
        }

        return view1
    }

    private fun fetchHotNewsHeadlines() {

        subscription.add(
            topNewsHeadlinesVM.getTopNewsHeadlinesData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress(view1.progress, true) }
                .doOnTerminate { showProgress(view1.progress, false) }
                .subscribe(
                    {
                        if (it != null && topNewsHeadlinesVM.isSuccessResponse) {

                            if (topNewsHeadlinesVM.dataResponse != null && topNewsHeadlinesVM.dataResponse?.articles != null && topNewsHeadlinesVM.dataResponse?.articles!!.isNotEmpty()) {

                                App.topNewsHeadlinesResponse = topNewsHeadlinesVM.dataResponse
                                topNewsHeadlinesResponse = topNewsHeadlinesVM.dataResponse!!


                                Timber.d("pokemon list success")

                                // show pokemon items in recycler view
                                view1.ll_no_results_found?.visibility = View.GONE

                                var adapter = topNewsHeadlinesResponse!!.articles?.let { it1 ->
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

        fetchHotNewsHeadlines()
    }
}
