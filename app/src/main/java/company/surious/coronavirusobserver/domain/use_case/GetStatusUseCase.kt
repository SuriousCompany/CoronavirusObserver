package company.surious.coronavirusobserver.domain.use_case

import company.surious.coronavirusobserver.domain.entities.StatusEntity
import company.surious.coronavirusobserver.domain.repositories.StatusRepository
import company.surious.coronavirusobserver.domain.use_case.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetStatusUseCase @Inject constructor(private val statusRepository: StatusRepository) :
    SingleUseCase<Void?, StatusEntity>() {

    override fun createSingle(params: Void?): Single<StatusEntity> = statusRepository.getStatus()
}