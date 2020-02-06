package company.surious.coronovirusobserver.domain.repositories

import company.surious.coronovirusobserver.domain.entities.StatusEntity
import io.reactivex.Single

interface StatusRepository {
    fun getStatus(): Single<StatusEntity>
}