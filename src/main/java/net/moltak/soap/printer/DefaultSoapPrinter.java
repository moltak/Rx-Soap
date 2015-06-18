package net.moltak.soap.printer;

import net.moltak.soap.utility.UnformatedXmlPrettyFormmater;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by moltak on 15. 2. 11..
 */
public class DefaultSoapPrinter implements SoapPrinter {
    @Override
    public void printLog(String soapAction, String method, SoapSerializationEnvelope envelope,
                         HttpTransportSE androidHttpTransport, long processingTime, String url) throws SoapFault {
        try {
            System.out.println("URL: " + url);
            System.out.println("Response code: " + androidHttpTransport.getServiceConnection().getResponseCode());

            String prefix = String.format("-->[%s] -- begin\n", url);
            String requestRaw
                    = UnformatedXmlPrettyFormmater.prettyXml(androidHttpTransport.requestDump);
            String responseRaw
                    = UnformatedXmlPrettyFormmater.prettyXml(androidHttpTransport.responseDump);
            String responseSuffix = "\n<-- response\n";
            String result = String.format("\n**--- result ---**\n%s\n**--------------**\n",
                    envelope.getResponse().toString());
            String processingTimeStr = String.format("Processing Time: %d(ms)\n", processingTime);
            String suffix = String.format("<-- [%s] -- end\n", url);
            String testLog = prefix + requestRaw + responseSuffix + responseRaw + result + 
                    processingTimeStr + suffix;
            System.out.println(testLog);
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
