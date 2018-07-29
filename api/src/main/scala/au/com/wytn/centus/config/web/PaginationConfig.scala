package au.com.wytn.centus.config.web

import java.util.{List => JList}
import scala.collection.JavaConverters._


object PaginationConfig {
    private val pages = Seq[Integer](20, 50, 100)

    def apply(): PaginationConfig = {
        new PaginationConfig(pages)
    }
}

case class PaginationConfig
(
    pages: Seq[Integer]
) {
    val defaultPage: Integer = pages.head

    val pageList: JList[Integer] = pages.asJava

    def sanitize(page: Integer): Integer = {
        if (pages.contains(page)) page
        else defaultPage
    }
}
