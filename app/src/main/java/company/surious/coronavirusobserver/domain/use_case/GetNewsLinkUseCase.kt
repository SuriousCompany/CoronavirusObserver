package company.surious.coronavirusobserver.domain.use_case

import company.surious.coronavirusobserver.domain.repositories.NewsRepository
import company.surious.coronavirusobserver.domain.use_case.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetNewsLinkUseCase @Inject constructor(private val newsRepository: NewsRepository) :
    SingleUseCase<Void?, String>() {

    override fun createSingle(params: Void?): Single<String> = newsRepository.getNewsLink()
}