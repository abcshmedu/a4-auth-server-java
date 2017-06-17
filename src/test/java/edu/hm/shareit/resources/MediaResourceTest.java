package edu.hm.shareit.resources;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.business.MediaService;
import edu.hm.shareit.business.MediaServiceResult;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MediaResourceTest {

    private MediaResource mediaResource;
    private MediaService mediaServiceMock;
    /* Use Dependency-Injection to inject a mockup of MediaService which
     * is required by MediaResource. */
    private Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            mediaServiceMock = mock(MediaService.class);
            bind(MediaService.class).toInstance(mediaServiceMock);
        }
    });

    @Before
    public void setup() {
        // Inject our mediaServiceMock into mediaResource
        mediaResource = injector.getInstance(MediaResource.class);
        //injector.injectMembers(mediaResource);
    }

    @Test
    public void createBook() throws Exception {
        // Adding valid Book
        when(mediaServiceMock.addBook(null)).thenReturn(MediaServiceResult.SUCCESS);
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), mediaResource.createBook(null).getStatus());

        // Adding invalid Book
        when(mediaServiceMock.addBook(null)).thenReturn(MediaServiceResult.ERROR);
        assertEquals(MediaServiceResult.ERROR.getStatusCode(), mediaResource.createBook(null).getStatus());
    }

    @Test
    public void getBook() throws Exception {
        // Request valid Book
        when(mediaServiceMock.getBook("")).thenReturn(new Book());
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), mediaResource.getBook("").getStatus());

        // Request invalid Book
        when(mediaServiceMock.getBook("")).thenReturn(null);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                mediaResource.getBook("0001").getStatusInfo().getReasonPhrase());
    }

    @Test
    public void getBooks() throws Exception {
        when(mediaServiceMock.getBooks()).thenReturn(new Medium[0]);
        assertEquals(Response.ok().build().getStatus(), mediaResource.getBooks().getStatus());
    }

    @Test
    public void updateBook() throws Exception {
        // MediaServiceResult.SUCCESS
        when(mediaServiceMock.updateBook(null, "")).thenReturn(MediaServiceResult.SUCCESS);
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(),
                mediaResource.updateBook(null, "").getStatus());

        // MediaServiceResult.ERROR
        when(mediaServiceMock.updateBook(null, "")).thenReturn(MediaServiceResult.ERROR);
        assertEquals(MediaServiceResult.ERROR.getStatusCode(),
                mediaResource.updateBook(null, "").getStatus());

        // MediaServiceResult.MEDIUM_ID_IMMUTABLE
        when(mediaServiceMock.updateBook(null, "")).thenReturn(MediaServiceResult.MEDIUM_ID_IMMUTABLE);
        assertEquals(MediaServiceResult.MEDIUM_ID_IMMUTABLE.getStatusCode(),
                mediaResource.updateBook(null, "").getStatus());

        // MediaServiceResult.MEDIUM_NOT_FOUND
        when(mediaServiceMock.updateBook(null, "")).thenReturn(MediaServiceResult.MEDIUM_NOT_FOUND);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                mediaResource.updateBook(null, "").getStatusInfo().getReasonPhrase());

        // MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION
        when(mediaServiceMock.updateBook(null, "")).thenReturn(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                mediaResource.updateBook(null, "").getStatusInfo().getReasonPhrase());
    }

    @Test
    public void createDisc() throws Exception {
        // Adding valid Disc
        when(mediaServiceMock.addDisc(null)).thenReturn(MediaServiceResult.SUCCESS);
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), mediaResource.createDisc(null).getStatus());

        // Adding invalid Disc
        when(mediaServiceMock.addDisc(null)).thenReturn(MediaServiceResult.ERROR);
        assertEquals(MediaServiceResult.ERROR.getStatusCode(), mediaResource.createDisc(null).getStatus());
    }

    @Test
    public void getDisc() throws Exception {
        // Request valid Disc
        when(mediaServiceMock.getDisc("")).thenReturn(new Disc());
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), mediaResource.getDisc("").getStatus());

        // Request invalid Disc
        when(mediaServiceMock.getDisc("")).thenReturn(null);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                mediaResource.getDisc("").getStatusInfo().getReasonPhrase());
    }

    @Test
    public void getDiscs() throws Exception {
        when(mediaServiceMock.getDiscs()).thenReturn(new Medium[0]);
        assertEquals(Response.ok().build().getStatus(), mediaResource.getDiscs().getStatus());
    }

    @Test
    public void updateDisc() throws Exception {
        // MediaServiceResult.SUCCESS
        when(mediaServiceMock.updateDisc(null, "")).thenReturn(MediaServiceResult.SUCCESS);
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(),
                mediaResource.updateDisc(null, "").getStatus());

        // MediaServiceResult.ERROR
        when(mediaServiceMock.updateDisc(null, "")).thenReturn(MediaServiceResult.ERROR);
        assertEquals(MediaServiceResult.ERROR.getStatusCode(),
                mediaResource.updateDisc(null, "").getStatus());

        // MediaServiceResult.MEDIUM_ID_IMMUTABLE
        when(mediaServiceMock.updateDisc(null, "")).thenReturn(MediaServiceResult.MEDIUM_ID_IMMUTABLE);
        assertEquals(MediaServiceResult.MEDIUM_ID_IMMUTABLE.getStatusCode(),
                mediaResource.updateDisc(null, "").getStatus());

        // MediaServiceResult.MEDIUM_NOT_FOUND
        when(mediaServiceMock.updateDisc(null, "")).thenReturn(MediaServiceResult.MEDIUM_NOT_FOUND);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                mediaResource.updateDisc(null, "").getStatusInfo().getReasonPhrase());

        // MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION
        when(mediaServiceMock.updateDisc(null, "")).thenReturn(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                mediaResource.updateDisc(null, "").getStatusInfo().getReasonPhrase());
    }
}