package co.innovaciones.bflow_server.model


enum class TaskStage {

    SLAB_DOWN,
    PLATE_HEIGH,
    ROOF_COVER,
    LOCK_UP,
    CABINETS,
    PCI;

    override fun toString(): String {
        return when (this) {
            SLAB_DOWN -> "Slab Down"
            PLATE_HEIGH -> "Plate Height"
            ROOF_COVER -> "Roof Cover"
            LOCK_UP -> "Lock Up"
            CABINETS -> "Cabinets"
            PCI -> "PCI"
        }
    }

}
