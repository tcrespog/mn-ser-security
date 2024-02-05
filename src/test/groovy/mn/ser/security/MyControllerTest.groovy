package mn.ser.security

import spock.lang.Specification

import groovy.transform.CompileStatic
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.AccessRefreshToken
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class MyControllerTest extends Specification {

    @Inject
    @Client('/')
    HttpClient client

    void 'log in'() {
        given:
        def loginResp = doJwtLogin()

        expect:
        loginResp
    }

    private AccessRefreshToken doJwtLogin() {
        HttpRequest loginRequest = HttpRequest.create(HttpMethod.POST, '/login')
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials('username', 'password'))

        HttpResponse<AccessRefreshToken> loginResponse = client.toBlocking().exchange(loginRequest, AccessRefreshToken)

        return loginResponse.body()
    }

}
