package mn.ser.security

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller
@CompileStatic
class MyController {

    @Get
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Status(HttpStatus.OK)
    void ping() {
    }

    @Post('/pet')
    @Secured(SecurityRule.IS_ANONYMOUS)
    HttpResponse<PetCollection> pet(@Body PetCollection petCollection) {
        return HttpResponse.ok(petCollection)
    }


}
