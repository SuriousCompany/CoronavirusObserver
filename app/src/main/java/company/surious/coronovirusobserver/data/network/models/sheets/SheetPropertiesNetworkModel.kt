package company.surious.coronovirusobserver.data.network.models.sheets

data class SheetPropertiesNetworkModel(
    var sheetId: Int = -1,
    var title: String = "",
    var index: Int = 0,
    var sheetType: SheetType = SheetType.SHEET_TYPE_UNSPECIFIED,
    var gridProperties: GridProperties = GridProperties()
)