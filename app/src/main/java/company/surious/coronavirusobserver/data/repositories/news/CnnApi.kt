package company.surious.coronavirusobserver.data.repositories.news

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface CnnApi {
    @GET("world/live-news/coronavirus-pandemic-{month}-{day}-{year}-intl/index.html")
    fun getNewsPage(
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("day") day: String
    ): Single<ResponseBody>
}