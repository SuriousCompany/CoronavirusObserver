package company.surious.coronavirusobserver.data.repositories.status

import company.surious.coronavirusobserver.data.network.NetworkRequestWrapper
import company.surious.coronavirusobserver.data.repositories.status.mappers.StatusMapper
import company.surious.coronavirusobserver.domain.entities.StatusEntity
import company.surious.coronavirusobserver.domain.repositories.StatusRepository
import io.reactivex.Single


class StatusRepositoryImpl(
    private val api: CoronovirusApi,
    private val networkRequestWrapper: NetworkRequestWrapper
) :
    StatusRepository {

    override fun getStatus(): Single<StatusEntity> =
        networkRequestWrapper.wrap(api.getStatus().map(StatusMapper::map))
}