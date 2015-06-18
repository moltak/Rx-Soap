package net.moltak.soap;

import net.moltak.soap.adapter.BaseSoapAdapter;
import net.moltak.soap.adapter.SoapAdapter;
import net.moltak.soap.parser.AnnotationSoapParser;
import net.moltak.soap.parser.SoapParser;
import net.moltak.soap.printer.DefaultSoapPrinter;
import net.moltak.soap.printer.SoapPrinter;

/**
 * Created by moltak on 15. 2. 5..
 */
public class SoapAdapterFactory {

    public static class Builder<E> {
        private String baseUrl, webserviceName;

        private BaseSoapAdapter.LogLevel logLevel = BaseSoapAdapter.LogLevel.NONE;
        private boolean debug = false;
        private SoapParser soapParser = new AnnotationSoapParser<>();
        private SoapPrinter soapPrinter = new DefaultSoapPrinter();
        private int timeout = 5000;

        public Builder() {
            this.logLevel = BaseSoapAdapter.LogLevel.NONE;
            this.debug = false;
            this.soapPrinter = new DefaultSoapPrinter();
            this.soapParser = new AnnotationSoapParser<>();
        }

        public Builder enableDebug() {
            debug = true;
            return this;
        }
        
        public Builder setLogLevel(BaseSoapAdapter.LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder setEndpoint(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setSoapPrinter(SoapPrinter soapPrinter) {
            this.soapPrinter = soapPrinter;
            return this;
        }

        public Builder setWebserviceName(String webserviceName) {
            this.webserviceName = webserviceName;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public BaseSoapAdapter<E> build() {
            if(baseUrl == null) {
                throw new IllegalStateException("You have to set base url");
            }

            if(webserviceName == null) {
                throw new IllegalStateException("You have to set webservice name");
            }

            return new SoapAdapter<>(
                    baseUrl, webserviceName, debug, logLevel, soapPrinter,
                    soapParser, timeout);
        }
    }
}
