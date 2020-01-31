package company.surious.coronovirusobserver.data.mappers

import android.text.TextUtils
import company.surious.coronovirusobserver.data.network.models.StatusNetworkResponse
import company.surious.coronovirusobserver.domain.entities.CountryStatusEntity
import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.domain.entities.extensions.calculateStatus

object StatusMapper {
    private const val PROVINCE_INDEX = 0
    private const val COUNTRY_INDEX = 1
    private const val LAST_UPDATE_INDEX = 2
    private const val CONFIRMED_INDEX = 3
    private const val DEATHS_INDEX = 4
    private const val RECOVERED_INDEX = 5

    fun map(response: StatusNetworkResponse): StatusEntity =
        StatusEntity().apply {
            val rawCountryStatuses = response.values.map(::mapCountryStatus)
            countryStatus = mapCountryStatuses(rawCountryStatuses)
            calculateStatus()
        }


    private fun mapCountryStatus(fields: Array<String>) =
        CountryStatusEntity(
            arrayListOf(fields[PROVINCE_INDEX]),
            fields[COUNTRY_INDEX],
            fields[LAST_UPDATE_INDEX]
        ).apply {
            applyStatusStatistic(this, fields)
        }


    private fun applyStatusStatistic(status: CountryStatusEntity, fields: Array<String>) {
        with(status) {
            when (fields.size) {
                4 -> infected = sheetsTextToInt(fields[CONFIRMED_INDEX])
                5 -> {
                    infected = sheetsTextToInt(fields[CONFIRMED_INDEX])
                    dead = sheetsTextToInt(fields[DEATHS_INDEX])
                }
                6 -> {
                    infected = sheetsTextToInt(fields[CONFIRMED_INDEX])
                    dead = sheetsTextToInt(fields[DEATHS_INDEX])
                    recovered = sheetsTextToInt(fields[RECOVERED_INDEX])
                }
            }
        }
    }

    private fun sheetsTextToInt(text: String) =
        if (TextUtils.isEmpty(text)) {
            0
        } else {
            text.toInt()
        }

    private fun mapCountryStatuses(statuses: List<CountryStatusEntity>): HashMap<String, CountryStatusEntity> {
        val res = HashMap<String, CountryStatusEntity>()
        statuses.forEach { status ->
            val existingCountryStatus = res[status.countryName]
            existingCountryStatus?.let {
                it.dead += status.dead
                it.infected += status.infected
                it.recovered += status.recovered
                it.provinces.addAll(status.provinces)
            } ?: run {
                res.put(status.countryName, status)
            }
        }
        return res
    }

}