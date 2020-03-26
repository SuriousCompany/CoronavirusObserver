package company.surious.coronavirusobserver.data.repositories.status

import company.surious.coronavirusobserver.data.repositories.status.mappers.StatusMapper
import company.surious.coronavirusobserver.domain.entities.StatusEntity
import company.surious.coronavirusobserver.domain.repositories.StatusRepository
import io.reactivex.Single


class StatusRepositoryImpl(private val api: CoronovirusApi) :
    StatusRepository {
    override fun getStatus(): Single<StatusEntity> =
        api.getStatus().map(StatusMapper::map)
}