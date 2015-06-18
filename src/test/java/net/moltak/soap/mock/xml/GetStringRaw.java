package net.moltak.soap.mock.xml;

/**
 * Created by moltak on 15. 6. 18..
 */
public class GetStringRaw {
    public static final String xml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "  <soap:Body>\n" +
            "    <get_string_response xmlns=\"http://driverws.elasticbeanstalk.com/\">\n" +
            "      <get_string>hello soap</get_string>\n" +
            "    </get_string_response>\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";
}
