package net.moltak.soap;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import net.moltak.soap.adapter.BaseSoapAdapter;
import net.moltak.soap.mock.SoapDispatcher;
import net.moltak.soap.mock.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by moltak on 15. 6. 18..
 */
public class MockServerTest {

    MockWebServer mockWebServer;
    SoapAdapterFactory.Builder soapAdapterFactory;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new SoapDispatcher().getDispatcher());
        mockWebServer.start();

        soapAdapterFactory = new SoapAdapterFactory.Builder<>()
                .setEndpoint(mockWebServer.getUrl("").toString())
                .enableDebug();
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void testGetString() throws Exception {
        BaseSoapAdapter adapter = soapAdapterFactory
                .setWebserviceName("/WebServices/Driver.asmx?op=getString")
                .build();

        Observable<String> o
                = adapter.create(new GetStringRequest("name"))
                .async(String.class);

        String r = o.toBlocking().toFuture().get();
        assertNotNull(r);
        assertThat(r, is("hello soap"));
    }

    @Test
    public void testGetObject() throws Exception {
        BaseSoapAdapter adapter = soapAdapterFactory
                .setWebserviceName("/WebServices/Driver.asmx?op=getObject")
                .build();

        Observable<GetObjectResponse> o
                = adapter.create(new GetObjectRequest("name"))
                .async(GetObjectResponse.class);

        GetObjectResponse r = o.toBlocking().toFuture().get();
        assertNotNull(r);
        assertThat(r.getName(), is("moltak"));
        assertThat(r.getGender(), is("male"));
    }

    @Test
    public void testGetList() throws Exception {
        BaseSoapAdapter adapter = soapAdapterFactory
                .setWebserviceName("/WebServices/Driver.asmx?op=getList")
                .build();

        Observable<GetListResponse> o
                = adapter.create(new GetListRequest("name"))
                .async(GetListResponse.class);

        GetListResponse r = o.toBlocking().toFuture().get();
        assertNotNull(r);
        assertThat(r.getSoapLists().length, not(0));
        assertThat(r.getSoapLists()[0].getName(), is("name 1"));
    }
}
