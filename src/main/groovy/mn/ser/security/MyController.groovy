package mn.ser.security

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller('/')
@CompileStatic
class MyController {

    @Get('/')
    @Secured(SecurityRule.IS_AUTHENTICATED)
    HttpResponse ping() {
        return HttpResponse.ok()
    }

    @Post('/pet')
    @Secured(SecurityRule.IS_ANONYMOUS)
    HttpResponse<PetCollection> pet(@Body PetCollection petCollection) {
        return HttpResponse.ok(petCollection)
    }


}
