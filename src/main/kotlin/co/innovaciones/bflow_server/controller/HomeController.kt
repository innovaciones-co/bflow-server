package co.innovaciones.bflow_server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {
    @Value("\${app.name}")
    private lateinit var appName: String

    @Value("\${springdoc.swagger-ui.path:/swagger-ui/index.html}")
    private lateinit var swaggerPath: String

    @Autowired
    var buildProperties: BuildProperties? = null

    @GetMapping("/")
    fun index(model: Model) : String {

        model.addAttribute("appName", appName)
        model.addAttribute("appVersion", buildProperties?.version)
        model.addAttribute("swaggerPath", swaggerPath)
        return "index"
    }

}
