package net.moltak.soap.adapter;

import net.moltak.soap.SoapTransmiter;
import net.moltak.soap.model.BaseRequest;
import net.moltak.soap.model.SoapBaseResponse;
import net.moltak.soap.parser.SoapParser;
import net.moltak.soap.printer.SoapPrinter;
import org.xmlpull.v1.XmlPullParserException;
import rx.Observable;
import rx.Subscriber;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by moltak on 15. 2. 5..
 */
public class SoapAdapter<E> implements BaseSoapAdapter<E> {

    private final String baseUrl, webServiceName;
    private final boolean debug;
    private final LogLevel logLevel;
    private final SoapPrinter soapPrinter;
    private final int timeout;

    private BaseRequest request;
    private SoapParser soapParser;
    private SoapBaseResponse soapResponse;

    public SoapAdapter(String baseUrl, String webserviceName, boolean debug, LogLevel logLevel,
                       SoapPrinter soapPrinter, SoapParser soapParser, int timeout) {
        this.baseUrl = baseUrl;
        this.webServiceName = webserviceName;
        this.debug = debug;
        this.logLevel = logLevel;
        this.soapPrinter = soapPrinter;
        this.soapParser = soapParser;
        this.timeout = timeout;
    }

    @Override
    public BaseSoapAdapter<E> create(BaseRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public BaseSoapAdapter<E> setParser(SoapParser soapParser) {
        this.soapParser = soapParser;
        return this;
    }

    @Override
    public Observable<E> async(final Class clazz) {
        return Observable.create(new Observable.OnSubscribe<E>() {
            @Override
            public void call(Subscriber<? super E> subscriber) {
                try {
                    subscriber.onNext(get(clazz));
                    subscriber.onCompleted();
                } catch (InvocationTargetException | IllegalAccessException | XmlPullParserException| IOException |
                        InstantiationException | ParseException | NoSuchMethodException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private E get(Class clazz) throws InvocationTargetException, IllegalAccessException, IOException,
            XmlPullParserException, ParseException, InstantiationException, NoSuchMethodException {
        SoapBaseResponse soapBaseResponse = SoapTransmiter.sendRequestUsingSoap(
                baseUrl,
                webServiceName,
                request.getRequestName(),
                baseUrl + request.getRequestName(),
                request.getParams(),
                debug,
                logLevel,
                soapPrinter,
                timeout);

        this.soapResponse = soapBaseResponse;

        // safe type cast and return
        return (E) soapParser.parsing(soapBaseResponse.getResponse(), clazz);
    }

    @Override
    public Map<String, String> getParams() throws InvocationTargetException, IllegalAccessException {
        return request.getParams();
    }

    @Override
    public BaseRequest getParameterObject() {
        return request;
    }

    @Override
    public SoapBaseResponse getSoapResponse() {
        return soapResponse;
    }
}
