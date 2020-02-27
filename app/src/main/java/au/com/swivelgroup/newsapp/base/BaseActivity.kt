package au.com.swivelgroup.newsapp.base

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import java.net.ConnectException

open class BaseActivity: AppCompatActivity() {

    protected open val subscription = CompositeDisposable()

    /**
     * Activity onStop
     */
    override fun onStop() {
        super.onStop()

        // clear subscription
        subscription.clear()
    }

    /**
     * handle network error
     */
    fun handleNetworkError(e: Throwable) {
        e.printStackTrace()
        if (e is ConnectException) {
            showToast("Please connect to Internet")
        } else {
            showToast("Please try again")
        }
    }

    /**
     * Show toast message
     *
     * @param msg:String
     */
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
}