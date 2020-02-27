package au.com.swivelgroup.newsapp.ui.main.tabs.topnews

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.data.remote.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_hot_news_headline.view.*

class TopNewsHeadlinesAdapter(private val items: Array<Article>) :
    RecyclerView.Adapter<TopNewsHeadlinesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.list_item_hot_news_headline, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Article) {

            itemView.tv_headline_title.text = item.title
            itemView.tv_news_description.text = item.description

            if(item.urlToImage != null && item.urlToImage.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(item.urlToImage)
                    .into(itemView.iv_news_headline)
            } else {
                itemView.iv_news_headline.visibility = View.GONE
            }

            itemView.setOnClickListener {
                val context = itemView.context
//                val galleryItemIntent = Intent(context, PokemonDetailsActivity::class.java)

//                context.startActivity(galleryItemIntent)
            }
        }
    }
}