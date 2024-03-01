package co.innovaciones.bflow_server.model


import com.fasterxml.jackson.annotation.JsonProperty

data class TaskTemplateDTO(
    //val attachments: Set<Long>?,
    val description: String?,
    //val endDate: LocalDate?,
    //val jobId: Long?,
    val name: String,
    //val order: Int?,
    //val parentTaskId: Long?,
    //val progress: Double?,
    val stage: TaskStage?,
    //val startDate: LocalDate,
    val status: TaskStatus?,

    @JsonProperty("supplier_id")
    val supplierId: Long?,
)