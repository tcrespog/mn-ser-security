package mn.ser.security

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailureReason
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Singleton

@Singleton
@CompileStatic
class AuthProvider<B> implements HttpRequestAuthenticationProvider<B> {
    AuthenticationResponse authenticate(@Nullable HttpRequest<B> requestContext,
                                        @NonNull AuthenticationRequest<String, String> authRequest) {
        (authRequest.identity == 'username') && (authRequest.secret == "password")
                ? AuthenticationResponse.success("user")
                : AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)
    }
}
