package company.surious.coronovirusobserver.domain.entities

data class SheetEntity(
    var sheetId: Int = -1,
    var title: String = "",
    var index: Int = 0,
    var rowCount: Int = -1,
    var columnCount: Int = -1
)
