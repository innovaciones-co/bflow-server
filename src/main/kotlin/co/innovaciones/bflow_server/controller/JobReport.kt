package co.innovaciones.bflow_server.controller

import co.innovaciones.bflow_server.model.JobReportDTO

interface JobReport {
    fun generateReport(jobReports: Map<Long, List<JobReportDTO>>, reportDate: String): ByteArray
}