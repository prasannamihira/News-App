package au.com.swivelgroup.newsapp.data.remote

/**
 * top new head lines response data
 *
 * @param articles
 * @param status
 * @param totalResults
 */
data class TopNewsHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: Array<Article>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TopNewsHeadlinesResponse

        if (status != other.status) return false
        if (totalResults != other.totalResults) return false
        if (articles != null) {
            if (other.articles == null) return false
            if (!articles.contentEquals(other.articles)) return false
        } else if (other.articles != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + totalResults
        result = 31 * result + (articles?.contentHashCode() ?: 0)
        return result
    }
}

/**
 * article data
 *
 * @param author
 * @param content
 * @param description
 * @param publishedAt
 * @param source
 * @param title
 * @param url
 * @param urlToImage
 */
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?
)

/**
 * source of news
 *
 * @param id
 * @param name
 */
data class Source(val id: String?, val name: String?)
