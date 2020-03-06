package company.surious.coronavirusobserver.data.mappers

import company.surious.coronavirusobserver.data.network.models.StatusFeature
import company.surious.coronavirusobserver.data.network.models.StatusNetworkResponse
import company.surious.coronavirusobserver.domain.entities.CountryStatusEntity
import company.surious.coronavirusobserver.domain.entities.StatusEntity
import company.surious.coronavirusobserver.domain.entities.extensions.calculateStatus

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