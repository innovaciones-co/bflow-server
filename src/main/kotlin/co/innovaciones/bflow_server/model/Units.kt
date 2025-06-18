package co.innovaciones.bflow_server.model

enum class Units(val abbreviation: String) {
    ACRES("ac"),
    AMOUNT("amount"),
    CUBIC_FEET("cu ft"),
    CUBIC_METERS("cu m"),
    DAYS("days"),
    EACH("each"),
    FEET("ft"),
    GALLONS("gal"),
    HECTARES("ha"),
    HOURS("hrs"),
    INCHES("in"),
    KILOGRAMS("kg"),
    LITERS("L"),
    METERS("m"),
    MILLIMETERS("mm"),
    MONTHS("months"),
    PIECES("pcs"),
    POUNDS("lb"),
    SETS("sets"),
    SHEETS("sheets"),
    SQUARE_FEET("sq ft"),
    SQUARE_METERS("sq m"),
    TEXT("text"),
    TONS("t"),
    UNIT("unit"),
    WEEKS("wks");

    override fun toString(): String {
        return abbreviation
    }

    companion object {
        fun fromString(abbreviation: String): Units? {
            return entries.find { it.abbreviation == abbreviation }
        }
    }
}
