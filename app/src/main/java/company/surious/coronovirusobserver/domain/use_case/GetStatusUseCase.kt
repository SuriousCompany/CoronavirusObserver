package company.surious.coronovirusobserver.domain.use_case

import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.domain.repositories.ReportsRepository
import company.surious.coronovirusobserver.domain.repositories.SheetsRepository
import company.surious.mediator_domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetStatusUseCase @Inject constructor(
    private val sheetsRepository: SheetsRepository,
    private val reportsRepository: ReportsRepository
) :
    SingleUseCase<Void?, StatusEntity>() {

    override fun createSingle(params: Void?): Single<StatusEntity> =
        sheetsRepository.getSheets().flatMap { sheets ->
            reportsRepository.getAllReports(sheets[0].title).map { reports ->
                StatusEntity().apply {
                    reports.forEach { report ->
                        infected += report.infected
                        dead += report.dead
                        recovered += report.recovered
                    }
                }

            }
        }
}