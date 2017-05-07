package edu.hm.shareit.business;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class MediaServiceResultTest {
    private static final MediaServiceResult msr = MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION;

    @Test
    public void getStatusCode() throws Exception {
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), msr.getStatusCode());
    }

    @Test
    public void getFamily() throws Exception {
        assertEquals(Response.Status.Family.familyOf(Response.Status.BAD_REQUEST.getStatusCode()), msr.getFamily());
    }

    @Test
    public void getReasonPhrase() throws Exception {
        String expected = "Invalid update-information.";
        assertEquals(expected, msr.getReasonPhrase());
    }

}