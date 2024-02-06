package mn.ser.security

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailureReason
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.reactivex.Flowable
import jakarta.inject.Singleton
import org.reactivestreams.Publisher

@Singleton
@CompileStatic
class AuthProvider implements AuthenticationProvider<HttpRequest<?>> {

    @Override
    Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authRequest) {
        AuthenticationResponse result = (authRequest.identity == 'username') && (authRequest.secret == "password")
                ? AuthenticationResponse.success("user")
                : AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)

        return Flowable.just(result) as Publisher<AuthenticationResponse>
    }
}
