package co.innovaciones.bflow_server.controller

import com.itextpdf.kernel.events.Event
import com.itextpdf.kernel.events.IEventHandler
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.layout.Document

class StartPageEventHandler : IEventHandler {
    override fun handleEvent(event: Event?) {
        val docEvent = event as PdfDocumentEvent
        val pdfDoc = docEvent.document

        PdfUtils.addHeaderWithLogo(Document(pdfDoc))
    }
}