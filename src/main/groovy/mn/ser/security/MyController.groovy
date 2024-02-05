package mn.ser.security

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller('/')
@Secured(SecurityRule.IS_AUTHENTICATED)
@CompileStatic
class MyController {

    @Get('/')
    HttpResponse ping() {
        return HttpResponse.ok()
    }

}
