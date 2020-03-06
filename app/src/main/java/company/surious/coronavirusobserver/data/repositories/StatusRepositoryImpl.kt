package company.surious.coronavirusobserver.data.repositories

import company.surious.coronavirusobserver.data.mappers.StatusMapper
import company.surious.coronavirusobserver.data.network.CoronovirusApi
import company.surious.coronavirusobserver.domain.entities.StatusEntity
import company.surious.coronavirusobserver.domain.repositories.StatusRepository
import io.reactivex.Single
import javax.inject.Inject

class StatusRepositoryImpl @Inject constructor(private val api: CoronovirusApi) :
    StatusRepository {
    override fun getStatus(): Single<StatusEntity> =
        api.getStatus().map(StatusMapper::map)
}