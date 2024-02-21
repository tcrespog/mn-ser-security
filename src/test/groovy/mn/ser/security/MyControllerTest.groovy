package mn.ser.security

import spock.lang.IgnoreRest
import spock.lang.Specification

import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
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

    void 'send a pet'() {
        when: 'send pets with the correct format'
        def pets = [
            pets: [
                    [name: 'Rex', type: 'dog'],
                    [name: 'Laika', type: 'dog'],
            ]
        ]
        def req = HttpRequest.POST('/pet', pets)
        def res = client.toBlocking().exchange(req, Map)

        then: 'the response is OK'
        res.status() == HttpStatus.OK
        res.body().pets.first().name == 'Rex'
        res.body().pets.first().type == 'DOG'
    }
    
    void 'send a pet malformed'() {
        when: 'send pets, one of them with a malformed name'
        def pets = [
                pets: [
                        [name: ['Rex'], type: 'dog'],
                        [name: 'Laika', type: 'dog'],
                ]
        ]
        def req = HttpRequest.POST('/pet', pets)
        def res = client.toBlocking().exchange(req, Map)

        then: 'the response is OK'
        res.status() == HttpStatus.OK
        res.body().pets.first().name == null
        res.body().pets.first().type == 'DOG'
    }

    private AccessRefreshToken doJwtLogin() {
        HttpRequest loginRequest = HttpRequest.create(HttpMethod.POST, '/login')
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials('username', 'password'))

        HttpResponse<AccessRefreshToken> loginResponse = client.toBlocking().exchange(loginRequest, AccessRefreshToken)

        return loginResponse.body()
    }

}
