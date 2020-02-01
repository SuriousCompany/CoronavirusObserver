package company.surious.coronovirusobserver.domain.entities.extensions

import company.surious.coronovirusobserver.domain.entities.StatusEntity

fun StatusEntity.calculateStatus() {
    totalDead = countryStatus.sumBy { it.dead }
    totalInfected = countryStatus.sumBy { it.infected }
    totalRecovered = countryStatus.sumBy { it.recovered }
}
