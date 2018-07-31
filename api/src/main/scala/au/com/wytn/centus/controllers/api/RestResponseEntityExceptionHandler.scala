package au.com.wytn.centus.controllers.api

import au.com.wytn.centus.domains.EntityNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Array(
        classOf[EntityNotFoundException]
    ))
    protected def handleEntityNotFound(ex: RuntimeException, request: WebRequest): ResponseEntity[AnyRef] = {
        val bodyOfResponse = "Requested entity is not found"
        handleExceptionInternal(ex.asInstanceOf[EntityNotFoundException], bodyOfResponse,
            new HttpHeaders, HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(Array(classOf[IllegalArgumentException], classOf[IllegalStateException]))
    protected def handleConflict(ex: RuntimeException, request: WebRequest): ResponseEntity[AnyRef] = {
        val bodyOfResponse = "This should be application specific"
        handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders, HttpStatus.CONFLICT, request)
    }

}
