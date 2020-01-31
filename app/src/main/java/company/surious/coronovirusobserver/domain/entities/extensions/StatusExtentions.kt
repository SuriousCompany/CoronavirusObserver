package company.surious.coronovirusobserver.domain.entities.extensions

import company.surious.coronovirusobserver.domain.entities.StatusEntity

fun StatusEntity.calculateStatus() {
    totalDead = countryStatus.values.sumBy { it.dead }
    totalInfected = countryStatus.values.sumBy { it.infected }
    totalRecovered = countryStatus.values.sumBy { it.recovered }
}
