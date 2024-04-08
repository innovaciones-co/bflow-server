package co.innovaciones.bflow_server.model


class JobReadDTO : JobDTO() {

    var client: ContactDTO? = null

    var user: UserDTO? = null

    var supervisor: UserDTO? = null

    var notes: Set<NoteDTO>? = null

    var files: Set<FileDTO>? = null

    var purchaseOrders: Set<PurchaseOrderDTO>? = null
}
