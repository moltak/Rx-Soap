package net.moltak.soap.mock.xml;

/**
 * Created by moltak on 15. 6. 18..
 */
public class GetObjectRaw {
    public static final String xml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "  <soap:Body>\n" +
            "    <infoResponse xmlns=\"http://driverws.elasticbeanstalk.com/\">\n" +
            "      <infoResult>\n" +
            "        <xs:schema xmlns=\"\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\" id=\"NewDataSet\">\n" +
            "          <xs:element name=\"NewDataSet\" msdata:IsDataSet=\"true\" msdata:UseCurrentLocale=\"true\">\n" +
            "            <xs:complexType>\n" +
            "              <xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n" +
            "                <xs:element name=\"Table\">\n" +
            "                  <xs:complexType>\n" +
            "                    <xs:sequence>\n" +
            "                      <xs:element name=\"name\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "                      <xs:element name=\"gender\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
            "                    </xs:sequence>\n" +
            "                  </xs:complexType>\n" +
            "                </xs:element>\n" +
            "              </xs:choice>\n" +
            "            </xs:complexType>\n" +
            "          </xs:element>\n" +
            "        </xs:schema>\n" +
            "        <diffgr:diffgram xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\" xmlns:diffgr=\"urn:schemas-microsoft-com:xml-diffgram-v1\">\n" +
            "          <NewDataSet xmlns=\"\">\n" +
            "            <Table diffgr:id=\"Table1\" msdata:rowOrder=\"0\" diffgr:hasChanges=\"inserted\">\n" +
            "              <name>moltak</name>\n" +
            "              <gender>male</gender>\n" +
            "            </Table>\n" +
            "          </NewDataSet>\n" +
            "        </diffgr:diffgram>\n" +
            "      </infoResult>\n" +
            "    </infoResponse>\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";
}
