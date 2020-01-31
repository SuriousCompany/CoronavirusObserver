package company.surious.coronovirusobserver.domain.entities

data class StatusEntity(
    var totalInfected: Int = 0,
    var totalDead: Int = 0,
    var totalRecovered: Int = 0,
    var lastUpdate: String = "",
    var countryStatus: Map<String, CountryStatusEntity> = HashMap()
)