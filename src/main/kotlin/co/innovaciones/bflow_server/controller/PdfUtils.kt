package co.innovaciones.bflow_server.controller

import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import org.springframework.core.io.ClassPathResource
import java.io.File

class PdfUtils {
    companion object {
        fun addHeaderWithLogo(document: Document) {
            try {
                val logoImagePath = "static/assets/img/sh_logo.png"
                val imageFile = File(ClassPathResource(logoImagePath).uri)
                if (imageFile.exists()) {
                    val logo = Image(ImageDataFactory.create(imageFile.absolutePath)).apply {
                        scaleToFit(40f, 40f)
                        setFixedPosition(520f, 780f)
                    }
                    document.add(logo)
                }
            } catch (e: Exception) {
                // Log or handle error if logo is not found
            }
        }
    }
}