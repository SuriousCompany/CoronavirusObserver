package company.surious.coronovirusobserver.data.network

import android.text.TextUtils
import company.surious.coronovirusobserver.data.network.models.ReportsNetworkResponse
import company.surious.coronovirusobserver.domain.entities.ReportEntity
import company.surious.coronovirusobserver.domain.repositories.ReportsRepository
import io.reactivex.Single
import javax.inject.Inject

class GoogleReportsRepository @Inject constructor(private val api: CoronovirusApi) :
    ReportsRepository {

    private companion object {
        private const val PROVINCE_INDEX = 0
        private const val COUNTRY_INDEX = 1
        private const val LAST_UPDATE_INDEX = 2
        private const val CONFIRMED_INDEX = 3
        private const val DEATHS_INDEX = 4
        private const val RECOVERED_INDEX = 5
    }

    override fun getAllReports(sheetTitle: String): Single<List<ReportEntity>> =
        api.getReports("values/$sheetTitle!A2:F1000").map(::map)

    private fun map(response: ReportsNetworkResponse) =
        response.values.map {
            val report =
                ReportEntity(
                    it[PROVINCE_INDEX],
                    it[COUNTRY_INDEX],
                    it[LAST_UPDATE_INDEX]
                )
            report.apply { applyStatistic(report, it) }
        }

    private fun applyStatistic(report: ReportEntity, fields: Array<String>) {
        when (fields.size) {
            4 -> report.infected = sheetsTextToInt(fields[CONFIRMED_INDEX])
            5 -> {
                report.infected = sheetsTextToInt(fields[CONFIRMED_INDEX])
                report.dead = sheetsTextToInt(fields[DEATHS_INDEX])
            }
            6 -> {
                report.infected = sheetsTextToInt(fields[CONFIRMED_INDEX])
                report.dead = sheetsTextToInt(fields[DEATHS_INDEX])
                report.recovered = sheetsTextToInt(fields[RECOVERED_INDEX])
            }
        }
    }

    private fun sheetsTextToInt(text: String) =
        if (TextUtils.isEmpty(text)) {
            0
        } else {
            text.toInt()
        }
}