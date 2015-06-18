package net.moltak.soap;

import net.moltak.soap.adapter.BaseSoapAdapter;
import net.moltak.soap.model.SoapBaseResponse;
import net.moltak.soap.printer.SoapPrinter;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by moltak on 15. 2. 5..
 */
public class SoapTransmiter {
    public static SoapBaseResponse sendRequestUsingSoap(String namespace, String webServiceName, String method,
                                              String soapAction, Map<String, String> params, 
                                              boolean debug, BaseSoapAdapter.LogLevel logLevel,
                                              SoapPrinter soapPrinter, int timeout)
            throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(namespace, method);

        if(params != null) {
            for(String i : params.keySet()) {
                request.addProperty(i, params.get(i));
            }
        }

        long begin = Calendar.getInstance().getTimeInMillis();

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(
                namespace + webServiceName,
                timeout);
        androidHttpTransport.debug = debug;
        androidHttpTransport.call(soapAction, envelope);

        String url = String.format("%s%s?op=%s", request.getNamespace(), webServiceName, request.getName());
        int responseCode = androidHttpTransport.getServiceConnection().getResponseCode();

        Object response = envelope.getResponse();
        if(logLevel == BaseSoapAdapter.LogLevel.FULL) {
            long end = Calendar.getInstance().getTimeInMillis();
            soapPrinter.printLog(soapAction, method, envelope, androidHttpTransport, end - begin, url);
        }
        return new SoapBaseResponse(
                responseCode,
                androidHttpTransport.getServiceConnection().getPort(),
                url,
                androidHttpTransport.getServiceConnection().getErrorStream(),
                response);
    }
}