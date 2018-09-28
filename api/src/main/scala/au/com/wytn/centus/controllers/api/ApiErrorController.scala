package au.com.wytn.centus.controllers.api

import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.web.servlet.error.{ErrorAttributes, ErrorController}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody, RestController}
import org.springframework.web.context.request.ServletWebRequest

object ApiErrorController {
    private final val PATH = "/api/error"
}

//@Controller
class ApiErrorController extends ErrorController {

    import ApiErrorController._

    override def getErrorPath: String = PATH

    @RequestMapping
    def error(request: HttpServletRequest): ResponseEntity[String] = {
        val statusCode = request.getAttribute("javax.servlet.error.status_code").asInstanceOf[Integer]
        val exception = request.getAttribute("javax.servlet.error.exception").asInstanceOf[Exception]
        new ResponseEntity[String](exception.getMessage, HttpStatus.valueOf(statusCode))

//        val status: HttpStatus = getStatus(request)
//        val body: ApiErrorResponse = getErrorAttributes(request, status)
//        new ResponseEntity[ApiErrorResponse](body, status)
    }


//    private def getErrorAttributes(request: HttpServletRequest, status: HttpStatus): ApiErrorResponse = {
//        val webRequest = new ServletWebRequest(request)
//
//        ApiErrorResponse(status, webRequest, errorAttributes)
//    }

//    private def getStatus(request: HttpServletRequest): HttpStatus = {
//        val statusCode = request.getAttribute("javax.servlet.error.status_code").asInstanceOf[Integer]
//        if (statusCode == null) return HttpStatus.INTERNAL_SERVER_ERROR
//        try
//            HttpStatus.valueOf(statusCode)
//        catch {
//            case ex: Exception =>
//                HttpStatus.INTERNAL_SERVER_ERROR
//        }
//    }
}
