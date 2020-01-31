package company.surious.coronovirusobserver.data.repositories

import company.surious.coronovirusobserver.data.mappers.StatusMapper
import company.surious.coronovirusobserver.data.network.CoronovirusApi
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.domain.repositories.StatusRepository
import io.reactivex.Single
import javax.inject.Inject

class GoogleStatusRepository @Inject constructor(private val api: CoronovirusApi) :
    StatusRepository {
    private val mapper = StatusMapper
    override fun getReportsOfSheet(sheetTitle: String): Single<StatusEntity> =
        api.getStatus("values/$sheetTitle!A2:F1000").map(mapper::map)
}