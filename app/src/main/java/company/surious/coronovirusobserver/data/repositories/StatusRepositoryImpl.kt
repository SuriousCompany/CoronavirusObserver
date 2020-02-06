package company.surious.coronovirusobserver.data.repositories

import company.surious.coronovirusobserver.data.mappers.StatusMapper
import company.surious.coronovirusobserver.data.network.CoronovirusApi
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.domain.repositories.StatusRepository
import io.reactivex.Single
import javax.inject.Inject

class StatusRepositoryImpl @Inject constructor(private val api: CoronovirusApi) :
    StatusRepository {
    override fun getStatus(): Single<StatusEntity> =
        api.getStatus().map(StatusMapper::map)
}