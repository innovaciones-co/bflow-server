package co.innovaciones.bflow_server.model

class TaskReadDTO: TaskDTO() {
    var supplier: ContactDTO? = null

    var attachments: List<FileDTO>? = null

    var purchaseOrder: PurchaseOrderDTO? = null
}