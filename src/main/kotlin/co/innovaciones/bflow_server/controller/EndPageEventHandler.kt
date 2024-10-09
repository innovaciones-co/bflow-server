package co.innovaciones.bflow_server.controller

import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.events.Event
import com.itextpdf.kernel.events.IEventHandler
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.canvas.PdfCanvas

class EndPageEventHandler : IEventHandler {
    override fun handleEvent(event: Event?) {
        val docEvent = event as PdfDocumentEvent
        val pdfDoc = docEvent.document
        val pdfPage = docEvent.page
        val pdfCanvas = PdfCanvas(pdfPage.lastContentStream, pdfPage.resources, pdfDoc)
        val pageNumber = pdfDoc.getPageNumber(pdfPage)
        val totalPageCount = pdfDoc.numberOfPages

        // Write the page number on each page
        val text = "Page $pageNumber of $totalPageCount"
        val x = 520f // Adjust X coordinate to place the text at the desired position
        val y = 20f  // Adjust Y coordinate for vertical position

        pdfCanvas.beginText()
        pdfCanvas.setFontAndSize(PdfFontFactory.createFont(), 6f)
        pdfCanvas.setColor(ColorConstants.BLACK, true)
        pdfCanvas.moveText(x.toDouble(), y.toDouble())
        pdfCanvas.showText(text)
        pdfCanvas.endText()
        pdfCanvas.release()
    }


}

