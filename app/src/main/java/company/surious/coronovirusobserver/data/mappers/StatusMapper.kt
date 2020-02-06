package company.surious.coronovirusobserver.data.mappers

import company.surious.coronovirusobserver.data.network.models.StatusFeature
import company.surious.coronovirusobserver.data.network.models.StatusNetworkResponse
import company.surious.coronovirusobserver.domain.entities.CountryStatusEntity
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.domain.entities.extensions.calculateStatus

object StatusMapper {

    fun map(response: StatusNetworkResponse): StatusEntity =
        StatusEntity().apply {
            countryStatus = ArrayList(response.features.map(::mapCountryStatus))
            lastUpdateMillis = countryStatus.maxBy { it.lastUpdateMillis }!!.lastUpdateMillis
            calculateStatus()
        }

    private fun mapCountryStatus(feature: StatusFeature) =
        with(feature.attributes) {
            CountryStatusEntity(
                countryRegion,
                lastUpdateMillis,
                confirmed,
                deaths,
                recovered
            )
        }
}