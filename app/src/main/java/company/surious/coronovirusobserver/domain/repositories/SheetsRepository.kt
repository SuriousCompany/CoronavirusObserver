package company.surious.coronovirusobserver.domain.repositories

import company.surious.coronovirusobserver.domain.entities.SheetEntity
import io.reactivex.Single

interface SheetsRepository {
    fun getSheets(): Single<List<SheetEntity>>
}