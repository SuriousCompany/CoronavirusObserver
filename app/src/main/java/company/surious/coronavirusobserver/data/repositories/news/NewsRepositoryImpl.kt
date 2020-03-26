package company.surious.coronavirusobserver.data.repositories.news

import company.surious.coronavirusobserver.domain.repositories.NewsRepository
import io.reactivex.Single
import java.util.*

class NewsRepositoryImpl(
    private val cnnApi: CnnApi,
    private val defaultNewsLink: String
) : NewsRepository {

    override fun getNewsLink(): Single<String> {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = format(calendar.get(Calendar.MONTH) + 1)
        val year = format(calendar.get(Calendar.YEAR) - 2000)
        return requestPageOfDay(format(day), month, year).onErrorResumeNext {
            requestPageOfDay(format(day - 1), month, year).onErrorReturnItem(defaultNewsLink)
        }
    }

    private fun requestPageOfDay(day: String, month: String, year: String) =
        cnnApi.getNewsPage(year, month, day).map {
            assembleLink(day, month, year)
        }

    private fun assembleLink(day: String, month: String, year: String) =
        "https://edition.cnn.com/world/live-news/coronavirus-outbreak-$month-$day-$year-intl-hnk/index.html"

    private fun format(number: Int) =
        if (number < 10) {
            "0$number"
        } else {
            number.toString()
        }

}