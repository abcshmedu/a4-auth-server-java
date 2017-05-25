package edu.hm.shareit.resources.filter;

import edu.hm.helper.Http;
import edu.hm.helper.HttpResult;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Filter for all incomming ShareIt API-requests. Checks if the requesting
 * header has the "Authorization"-field set and if so, validates the
 * supplied token by requesting the AuthentificationServer.
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthentificationFilter implements ContainerRequestFilter {

    private static final String CLIENT_ID = "shareit-super-token";
    private static final String AUTH_SERVER_ENDPOINT
            = "http://localhost:8082/auth/validate/" + CLIENT_ID;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationheader =
                containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationheader == null) {
            throw new NotAuthorizedException("No authentification-header");
        }

        HttpResult result = getTokenValidation(authorizationheader);

        if (result.getStatusCode() != Response.Status.OK.getStatusCode()) {
            throw new NotAuthorizedException(result.getStatusMessage());
        }
    }

    /**
     * Validates the token supplied by the user-request.
     *
     * @param token the token supplied in the HTTP-request-header.
     * @return the result of our request to the AuthentificationServer.
     */
    private HttpResult getTokenValidation(String token) {
        Http http = new Http();
        Map<String, String> header = new HashMap<>();
        header.put(HttpHeaders.AUTHORIZATION, token);
        return http.get(AUTH_SERVER_ENDPOINT, header);
    }
}
