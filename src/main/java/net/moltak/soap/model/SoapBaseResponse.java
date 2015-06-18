package net.moltak.soap.model;

import java.io.InputStream;

/**
 * Created by moltak on 15. 6. 17..
 */
public class SoapBaseResponse {
    private final int response_code;
    private final String url;
    private final Object response;
    private final InputStream error_stream;

    public SoapBaseResponse(int response_code, int port, String url, InputStream error_stream, Object response) {
        this.response_code = response_code;
        this.url = url;
        this.error_stream = error_stream;
        this.response = response;
    }

    public int getResponse_code() {
        return response_code;
    }

    public String getUrl() {
        return url;
    }

    public Object getResponse() {
        return response;
    }

    public InputStream getError_stream() {
        return error_stream;
    }
}
