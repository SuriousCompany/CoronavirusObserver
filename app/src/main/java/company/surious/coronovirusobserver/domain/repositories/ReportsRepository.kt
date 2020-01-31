package company.surious.coronovirusobserver.domain.repositories

import company.surious.coronovirusobserver.domain.entities.ReportEntity
import io.reactivex.Single

interface ReportsRepository {
    fun getAllReports(sheetTitle: String): Single<List<ReportEntity>>
}