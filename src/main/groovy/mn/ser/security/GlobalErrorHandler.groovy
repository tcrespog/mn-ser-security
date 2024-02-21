package mn.ser.security

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error

@Slf4j
@CompileStatic
@Controller('/error')
class GlobalErrorHandler {

    @Error(global = true)
    HttpResponse error(Throwable e) {
        log.error("Error", e)

        return HttpResponse.badRequest()
    }

}
