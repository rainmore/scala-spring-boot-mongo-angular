package au.com.wytn.centus.controllers.api

import java.time.LocalDateTime
import java.util
import java.util.{Map, Set}

import au.com.wytn.centus.domains.EntityNotFoundException
import javax.annotation.Nullable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.{DefaultErrorAttributes, ErrorAttributes}
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.{HttpHeaders, HttpMethod, HttpStatus, ResponseEntity}
import org.springframework.util.CollectionUtils
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.{RequestAttributes, WebRequest}
import org.springframework.web.util.WebUtils

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages = Array("au.com.wytn.centus.controllers", "org.springframework"))
class RestResponseEntityExceptionHandler @Autowired()(val errorAttributes: ErrorAttributes) extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Array(classOf[EntityNotFoundException]))
    def handleEntityNotFound(ex: RuntimeException, request: WebRequest): ResponseEntity[AnyRef] = {
        val bodyOfResponse = "Requested entity is not found"
        handleExceptionInternal(ex.asInstanceOf[EntityNotFoundException], bodyOfResponse,
            new HttpHeaders, HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(Array(classOf[IllegalArgumentException], classOf[IllegalStateException]))
    def handleConflict(ex: RuntimeException, request: WebRequest): ResponseEntity[AnyRef] = {
        val bodyOfResponse = "This should be application specific"
        handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders, HttpStatus.CONFLICT, request)
    }

    def handleHttpRequestMethodNotSupported1(ex: HttpRequestMethodNotSupportedException, request: WebRequest): ResponseEntity[AnyRef] = {
        val bodyOfResponse = "Request method is not allowed"
        handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders, HttpStatus.METHOD_NOT_ALLOWED, request)
    }

    override protected def handleExceptionInternal(ex: Exception,
                                          @Nullable body: Any,
                                          headers: HttpHeaders,
                                          status: HttpStatus,
                                          request: WebRequest): ResponseEntity[AnyRef] = {

        val attributes = new DefaultErrorAttributes()
        val body = attributes.getError(request)

        if (HttpStatus.INTERNAL_SERVER_ERROR == status)
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST)

        new ResponseEntity[AnyRef](buildApiErrorResponse(body: Any, ex, status, request), headers, status)
    }

    private def buildApiErrorResponse(body: Any, ex: Exception, status: HttpStatus, request: WebRequest): ApiErrorResponse = {
        ApiErrorResponse(ex, status, request, errorAttributes)
    }


}
