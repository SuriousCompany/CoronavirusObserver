package company.surious.coronovirusobserver.data.network.models.sheets

data class SheetsNetworkResponse(
    var sheets: List<SheetNetworkModel> = ArrayList()
)