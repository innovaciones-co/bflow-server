package co.innovaciones.bflow_server.controller

import co.innovaciones.bflow_server.model.JobReportDTO
import co.innovaciones.bflow_server.model.TaskReadDTO
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream


@Component
class PdfJobReport : JobReport {

    private val headerBackgroundColor = DeviceRgb(52, 131, 250)
    private val whiteColor = DeviceRgb(255, 255, 255)

    override fun generateReport(jobReports: Map<Long, List<JobReportDTO>>, reportDate: String): ByteArray {
        val defaultFont: PdfFont = PdfFontFactory.createFont(StandardFonts.HELVETICA)
        val outputStream = ByteArrayOutputStream()
        val pdfDocument = PdfDocument(PdfWriter(outputStream))
        val document = Document(pdfDocument).apply {
            setMargins(75f, 36f, 50f, 36f)
            setFontSize(10f)
            setFont(defaultFont)
        }

        pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, StartPageEventHandler())
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, EndPageEventHandler())

        // Add report title and logo
        document.apply {
            add(Paragraph("Work In Progress Report").apply {
                setBold()
                setFontSize(12f)
            })
            add(Paragraph("Report generated on: $reportDate").setFontSize(8f))
        }

        // Add supervisor sections
        jobReports.forEach { (_, jobs) ->
            addSupervisorSection(document, jobs)
        }

        document.close()
        return outputStream.toByteArray()
    }

    private fun addSupervisorSection(document: Document, jobs: List<JobReportDTO>) {
        val supervisorName = jobs.firstOrNull()?.job?.supervisor?.fullName ?: "Unknown"
        document.add(Paragraph("Supervisor: $supervisorName").apply {
            setBold()
            setMarginTop(20f)
            setMarginBottom(5f)
        })

        // Create and populate the table
        val table = Table(UnitValue.createPercentArray(floatArrayOf(2f, 3f, 1f, 1f, 5f))).apply {
            width = UnitValue.createPercentValue(100f)
        }

        addTableHeader(table, "Job Name")
        addTableHeader(table, "Address")
        addTableHeader(table, "Progress")
        addTableHeader(table, "Stage")
        addTableHeader(table, "Tasks")

        jobs.forEach { jobReport ->
            table.addCell(Paragraph(jobReport.job.name).setFontSize(8f))
            table.addCell(Paragraph(jobReport.job.address).setFontSize(8f))
            table.addCell(Cell().apply {
                add(Paragraph(String.format("%.2f", jobReport.job.progress) + "%").setFontSize(8f))
            })
            table.addCell(Paragraph(jobReport.job.stage.toString()).setFontSize(8f))
            table.addCell(Paragraph(formatTasks(jobReport.nextTask, jobReport.overdueTasks)).setFontSize(8f))
        }

        document.add(table)
        document.add(table)
        document.add(table)
        document.add(table)
    }

    private fun addTableHeader(table: Table, headerTitle: String) {
        table.addHeaderCell(Cell().apply {
            add(Paragraph(headerTitle).setFontSize(9f))
            setBackgroundColor(headerBackgroundColor)
            setFontColor(whiteColor)
            setTextAlignment(TextAlignment.CENTER)
            setPadding(1f)
        })
    }

    private fun formatTasks(nextTask: TaskReadDTO?, overdueTasks: List<TaskReadDTO>): String {
        return buildString {
            nextTask?.let {
                append("Next Task: ${it.name} (Due: ${it.endDate})\n")
            }
            if (overdueTasks.isNotEmpty()) {
                append("Overdue Tasks:\n")
                overdueTasks.forEach { task ->
                    append("${task.name} (Due: ${task.endDate})\n")
                }
            }
        }
    }

}

