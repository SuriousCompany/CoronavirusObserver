package company.surious.coronovirusobserver.data.repositories

import company.surious.coronovirusobserver.data.network.CoronovirusApi
import company.surious.coronovirusobserver.data.network.models.sheets.SheetType
import company.surious.coronovirusobserver.data.network.models.sheets.SheetsNetworkResponse
import company.surious.coronovirusobserver.domain.entities.SheetEntity
import company.surious.coronovirusobserver.domain.repositories.SheetsRepository
import io.reactivex.Single

class GoogleSheetsRepository(private val api: CoronovirusApi) : SheetsRepository {
    override fun getSheets(): Single<List<SheetEntity>> =
        api.getSheets().map(this::map)

    private fun map(networkModel: SheetsNetworkResponse): List<SheetEntity> {
        val sheets = networkModel.sheets.filter { it.properties.sheetType == SheetType.GRID }
        return sheets.map {
            with(it.properties) {
                SheetEntity(
                    sheetId,
                    title,
                    index,
                    gridProperties.rowCount,
                    gridProperties.columnCount
                )
            }

        }
    }

}