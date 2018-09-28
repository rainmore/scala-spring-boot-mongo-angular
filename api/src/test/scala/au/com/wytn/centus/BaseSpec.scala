package au.com.wytn.centus

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(classOf[SpringExtension])
@SpringBootTest
trait BaseSpec extends FunSpec {

}
