package co.innovaciones.bflow_server.mappers

interface Mapper<E, D> {
    fun mapToDTO(entity: E, dto: D): D
    fun mapToEntity(dto: D, entity: E): E
}