package co.innovaciones.bflow_server.model

enum class Units(val abbreviation: String) {
    FEET("ft"),
    METERS("m"),
    INCHES("in"),
    MILIMETERS("mm"),
    SQUARE_FEET("sq ft"),
    SQUARE_METERS("sq m"),
    ACRES("ac"),
    HECTARES("ha"),
    CUBIC_FEET("cu ft"),
    CUBIC_METERS("cu m"),
    GALLONS("gal"),
    LITERS("L"),
    POUNDS("lb"),
    KILOGRAMS("kg"),
    TONS("t"),
    EACH("each"),
    PIECES("pcs"),
    SETS("sets"),
    HOURS("hrs"),
    DAYS("days"),
    WEEKS("wks"),
    MONTHS("months");

    override fun toString(): String {
        return abbreviation
    }

    companion object {
        fun fromString(abbreviation: String): Units? {
            return entries.find { it.abbreviation == abbreviation }
        }
    }
}
