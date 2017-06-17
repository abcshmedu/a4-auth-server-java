package edu.hm.authentification;

import com.google.inject.Inject;
import edu.hm.authentification.data.AccountAccessMock;
import edu.hm.authentification.model.Credentials;
import edu.hm.authentification.model.Token;
import edu.hm.helper.Json;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Endpoints for authentification-services.
 */
@Path("auth")
public class AuthentificationResource {

    @Inject
    private static final AuthentificationServerImpl AUTHORIZATION_SERVER =
            new AuthentificationServerImpl(AccountAccessMock.getInstance());

    /**
     * Logs in a user.
     *
     * @param credentials user-credentials.
     * @return response according to result.
     * @see AuthentificationServerImpl#login(Credentials)
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        AuthentificationResult ar = AUTHORIZATION_SERVER.login(credentials);

        Response response;
        if (ar == AuthentificationResult.SUCCESS) {
            response = Response.status(AuthentificationResult.SUCCESS)
                    .entity(AUTHORIZATION_SERVER.getTokenFromCredentials(credentials))
                    .build();
        } else if (ar == AuthentificationResult.USER_INVALID_CREDENTIALS) {
            response = Response.status(AuthentificationResult.USER_INVALID_CREDENTIALS)
                    .entity(Json.serializeObject(ar))
                    .build();
        } else {
            response = Response.status(AuthentificationResult.USER_NOT_FOUND)
                    .entity(Json.serializeObject(ar))
                    .build();
        }

        return response;
    }

    /**
     * Logs out an active user.
     *
     * @param token the token to be invalidated.
     * @return response according to result.
     * @see AuthentificationServerImpl#logout(Token)
     */
    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(Token token) {
        AuthentificationResult ar = AUTHORIZATION_SERVER.logout(token);

        return Response.status(ar.getStatusCode()).build();
    }

    /**
     * Used by a client (app) to verify tokens.
     *
     * @param clientId    the name of the app/ the key for that app to access this server.
     * @param tokenString the token to verify.
     * @return response according to result.
     * @see AuthentificationServerImpl#validateToken(String, String)
     */
    @GET
    @Path("/validate/{clientid}")
    public Response validateToken(@PathParam("clientid") String clientId,
                                  @HeaderParam(HttpHeaders.AUTHORIZATION) String tokenString) {
        AuthentificationResult ar = AUTHORIZATION_SERVER.validateToken(clientId, tokenString);

        return Response.status(ar.getStatusCode()).build();
    }
}
