package au.com.wytn.centus.controllers.api

import java.time.LocalDateTime
import java.util

import javax.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.{HttpRequest, HttpStatus}
import org.springframework.validation.ObjectError
import org.springframework.web.context.request.{RequestAttributes, ServletWebRequest, WebRequest}


object ApiErrorResponse {

    def apply(status: HttpStatus, webRequest: WebRequest, errorAttributes: ErrorAttributes): ApiErrorResponse = {
        val error = errorAttributes.getError(webRequest)
        val path = errorAttributes.getErrorAttributes(webRequest, true).get("path").asInstanceOf[String]
        apply(error, status, path)
    }

    def apply(error: Throwable, status: HttpStatus, webRequest: WebRequest, errorAttributes: ErrorAttributes): ApiErrorResponse = {
        val path = errorAttributes.getErrorAttributes(webRequest, true).get("path").asInstanceOf[String]
        apply(error, status, path)
    }

    def apply(error: Throwable, status: HttpStatus, path: String): ApiErrorResponse = {
        ApiErrorResponse(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase,
            path,
            List(Error(error))
        )
    }

}

case class ApiErrorResponse
(
    timestamp: LocalDateTime,
    code: Integer,
    message: String,
    path: String,
    errors: List[Error]
)

object Error {

    def apply(objectError: ObjectError): Error = {
        new Error(objectError.getObjectName, objectError.getDefaultMessage)
    }

    def apply(exception: Throwable): Error = {
        new Error(exception.getClass.getSimpleName, exception.getMessage)
    }

}

case class Error
(
    code: String,
    message: String
)

