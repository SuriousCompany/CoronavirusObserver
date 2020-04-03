package company.surious.coronavirusobserver.domain.repositories

import io.reactivex.Single

interface NewsRepository {
    fun getNewsLink(): Single<String>
}