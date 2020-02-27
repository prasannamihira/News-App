package au.com.swivelgroup.newsapp.base

import android.app.AlertDialog
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.data.local.LocalDataSource
import au.com.swivelgroup.newsapp.data.remote.StatusResponse
import com.squareup.moshi.Moshi
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import java.net.ConnectException

open class BaseFragment: Fragment() {

    val subscription = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        subscription.clear()
    }

    /**
     * progress bar
     * @param view:ProgressBar
     * @param show:Boolean
     */
    fun showProgress(view: ProgressBar, show: Boolean) {

        if (show)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.INVISIBLE
    }

    /**
     * handle network error
     */
    fun handleNetworkError(e: Throwable) {
        e.printStackTrace()
        if (e is ConnectException) {
//            activity.showToast("Please connect to Internet")
        } else {
//            activity.showToast("Please try again")
        }
    }

    fun handleUnsuccessfulError(it: ResponseBody) {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<StatusResponse>(StatusResponse::class.java)
        val string = it.string()
        val status = jsonAdapter.fromJson(string)
        status?.status().let {
            when (status?.status()?.code) {
                BaseCallBack.UNAUTHORIZED_ERROR -> {
                    // handle unauthorized error
                }
                BaseCallBack.TOKEN_EXPIRE_ERROR -> {
//                    handle token expire error
                }
                else -> {
                    AlertDialog.Builder(activity)
                        .setMessage(resources.getString(R.string.error_server_problem))
                        .setPositiveButton("OK") { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                        .show()
                }
            }
        }
    }

}