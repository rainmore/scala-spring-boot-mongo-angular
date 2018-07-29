package au.com.wytn.centus.controllers.view

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ViewController {

    @RequestMapping(Array("/{path:[^\\.]+}/**"))
    def forward = "forward:/"

}
