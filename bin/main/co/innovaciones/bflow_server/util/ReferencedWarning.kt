package co.innovaciones.bflow_server.util

import java.util.ArrayList


class ReferencedWarning {

    var key: String? = null

    var params: ArrayList<Any> = ArrayList()

    fun addParam(`param`: Any?) {
        params.add(param!!)
    }

    fun toMessage(): String {
        var message = key!!
        if (params.isNotEmpty()) {
            message += "," + params.joinToString(",")
        }
        return message
    }

}
