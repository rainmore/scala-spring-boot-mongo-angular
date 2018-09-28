package au.com.wytn.centus.config.web

import java.util
import java.util.Locale

import au.com.wytn.centus.config.datetime.DateUtils
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.servlet.error.{DefaultErrorAttributes, ErrorAttributes}
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.format.FormatterRegistry
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.{RequestAttributes, WebRequest}
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}
import org.springframework.web.servlet.i18n.SessionLocaleResolver

import scala.collection.immutable.ListMap

@Configuration
class WebConfiguration extends WebMvcConfigurer {

    val MAX_FILE_UPLOAD_SIZE_IN_MB: Integer = 20

    private val defaultPaginationConfig:PaginationConfig = PaginationConfig()

    //    override def getMessageCodesResolver: MessageCodesResolver = new MessageCodesResolver {
    //        def resolveMessageCodes (errorCode: String, objectName: String, field: String, fieldType: Class[_] ): Array[String] = {
    //            Array[String] (errorCode)
    //        }
    //
    //        def resolveMessageCodes (errorCode: String, objectName: String): Array[String] = Array[String] (errorCode)
    //    }

    override def addArgumentResolvers(argumentResolvers: java.util.List[HandlerMethodArgumentResolver]) {
        val resolver: PageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver
        resolver.setFallbackPageable(PageRequest.of(0, defaultPaginationConfig.defaultPage))
        resolver.setPrefix("_")
        argumentResolvers.add(resolver)
    }

    override def addFormatters(registry: FormatterRegistry): Unit = {
        super.addFormatters(registry)
        registry.addConverter(new DateUtils.LocalDateConverter)
        registry.addConverter(new DateUtils.LocalDateTimeConverter)
        registry.addConverter(new DateUtils.LocalTimeConverter)
    }

    @Bean def paginationConfig: PaginationConfig = defaultPaginationConfig

    @Bean
    def localeResolver: LocaleResolver = {
        val ret: SessionLocaleResolver = new SessionLocaleResolver
        ret.setDefaultLocale(new Locale("en"))
        ret
    }

    //    @Bean
    //    @Inject
    //    def messageSource(messagesService: MessagesService): MessageSource = new AbstractMessageSource {
    //        override def resolveCode(code: String, locale: Locale): MessageFormat = {
    //            val message = messagesService.findOne(code, locale).map(_.getMessage)
    //            createMessageFormat(message.orNull, locale)
    //        }
    //    }


}
