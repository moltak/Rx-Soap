package net.moltak.soap.adapter;

import net.moltak.soap.model.BaseRequest;
import net.moltak.soap.model.SoapBaseResponse;
import net.moltak.soap.parser.SoapParser;
import rx.Observable;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by moltak on 15. 2. 5..
 */
public interface BaseSoapAdapter<E> {
    BaseRequest getParameterObject();
    Map<String, String> getParams() throws InvocationTargetException, IllegalAccessException;
    BaseSoapAdapter<? extends E> create(BaseRequest request);
    BaseSoapAdapter<? extends E> setParser(SoapParser soapParser);
    SoapBaseResponse getSoapResponse();

    Observable<E> async(Class clazz);

    public enum LogLevel {
        NONE, FULL
    }
}
