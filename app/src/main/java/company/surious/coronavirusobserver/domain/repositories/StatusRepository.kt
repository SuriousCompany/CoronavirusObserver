package company.surious.coronavirusobserver.domain.repositories

import company.surious.coronavirusobserver.domain.entities.StatusEntity
import io.reactivex.Single

interface StatusRepository {
    fun getStatus(): Single<StatusEntity>
}