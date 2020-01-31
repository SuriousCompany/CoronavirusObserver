package company.surious.coronovirusobserver.domain.entities

data class ReportEntity(
    var province: String = "",
    var country: String = "",
    var lastUpdate: String = "",
    var infected: Int = 0,
    var dead: Int = 0,
    var recovered: Int = 0
)