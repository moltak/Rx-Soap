package net.moltak.soap.mock;

import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import net.moltak.soap.mock.xml.GetObjectRaw;
import net.moltak.soap.mock.xml.GetListRaw;
import net.moltak.soap.mock.xml.GetStringRaw;

/**
 * Created by moltak on 15. 6. 18..
 */
public class SoapDispatcher {
    final Dispatcher dispatcher = new Dispatcher() {
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            switch (request.getPath()) {
                case "/WebServices/Driver.asmx?op=getString":
                    return new MockResponse().setBody(GetStringRaw.xml)
                            .addHeader("Content-Type", "text/xml; charset=utf-8")
                            .setResponseCode(200);

                case "/WebServices/Driver.asmx?op=getObject":
                    return new MockResponse().setBody(GetObjectRaw.xml)
                            .addHeader("Content-Type", "text/xml; charset=utf-8")
                            .setResponseCode(200);

                case "/WebServices/Driver.asmx?op=getList":
                    return new MockResponse().setBody(GetListRaw.xml)
                            .addHeader("Content-Type", "text/xml; charset=utf-8")
                            .setResponseCode(200);
            }

            return new MockResponse().setResponseCode(200);
        }
    };

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
