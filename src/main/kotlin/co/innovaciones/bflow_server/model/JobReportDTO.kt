package co.innovaciones.bflow_server.model

data class JobReportDTO(
    val job: JobReadDTO,
    val nextTask: TaskReadDTO?,
    val overdueTasks: List<TaskReadDTO>
)
