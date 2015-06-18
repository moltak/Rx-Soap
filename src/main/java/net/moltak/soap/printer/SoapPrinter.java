package net.moltak.soap.printer;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by moltak on 15. 2. 11..
 */
public interface SoapPrinter {
    public void printLog(String soapAction, String method, SoapSerializationEnvelope envelope,
                         HttpTransportSE androidHttpTransport, long wasteTime, String url) throws SoapFault;
}
