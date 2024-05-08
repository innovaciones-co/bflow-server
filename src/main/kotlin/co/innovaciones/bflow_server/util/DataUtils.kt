package co.innovaciones.bflow_server.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*


object DateUtils {
    fun formatOffsetDateTime(dateTime: OffsetDateTime?): String {
        val formatter = DateTimeFormatter.ofPattern("EEEE d MMM, yyyy", Locale.ENGLISH)
        return formatter.format(dateTime)
    }
}

