package edu.hm.shareit.resources;

import edu.hm.shareit.business.MediaServiceResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonTest {

    @Test
    public void serializeObject() throws Exception {
        assertEquals("null", Json.serializeObject(null));

        // Test if MediaServiceResult is serialized as expected.
        MediaServiceResult msr = MediaServiceResult.MEDIUM_MISSING_TITLE;
        assertEquals("{\"code\":400,\"detail\":\"Missing or invalid title.\"}", Json.serializeObject(msr));
    }
}