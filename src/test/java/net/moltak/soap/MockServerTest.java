package net.moltak.soap;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import net.moltak.soap.adapter.BaseSoapAdapter;
import net.moltak.soap.mock.SoapDispatcher;
import net.moltak.soap.mock.model.*;
import net.moltak.soap.printer.DefaultSoapPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

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

    /**
     * This is a reference function.
     */
    public void howToUse() {
        BaseSoapAdapter adapter = new SoapAdapterFactory.Builder<>()
                .setEndpoint("http://soap-endpoint.com/")
                .setWebserviceName("/WebServices/Soap.asmx")
                .enableDebug() // Enable debug mode for print log
                .setTimeout(5000) // Default timeout(5 seconds)
                .setLogLevel(BaseSoapAdapter.LogLevel.FULL) // It prints logs.
                .setSoapPrinter(new DefaultSoapPrinter()) // Default soap printer xml, http response codes
                .build();

        Observable<GetObjectResponse> o
                = adapter.create(new GetObjectRequest("name"))
                .async(GetObjectResponse.class);
        o.subscribe(new Action1<GetObjectResponse>() {
            @Override
            public void call(GetObjectResponse getObjectResponse) {
                // There are results.
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
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
