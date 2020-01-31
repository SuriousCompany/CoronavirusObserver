package company.surious.coronovirusobserver.domain.entities

data class StatusEntity(
    var infected: Int = 0,
    var dead: Int = 0,
    var recovered: Int = 0
)