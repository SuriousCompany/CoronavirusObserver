package company.surious.coronovirusobserver.domain.use_case

import company.surious.coronovirusobserver.domain.entities.StatusEntity
import company.surious.coronovirusobserver.domain.repositories.SheetsRepository
import company.surious.coronovirusobserver.domain.repositories.StatusRepository
import company.surious.mediator_domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetStatusUseCase @Inject constructor(
    private val sheetsRepository: SheetsRepository,
    private val statusRepository: StatusRepository
) :
    SingleUseCase<Void?, StatusEntity>() {

    override fun createSingle(params: Void?): Single<StatusEntity> =
        sheetsRepository.getSheets().flatMap { sheets ->
            statusRepository.getReportsOfSheet(sheets[0].title)
        }
}