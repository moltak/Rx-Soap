package net.moltak.soap.parser;

import org.ksoap2.serialization.SoapObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by kjo on 15. 2. 24..
 */

public class SoapListObjectParser<E> implements SoapParser {
    @SuppressWarnings("unchecked")
    @Override
    public Object parsing(Object response, Class outputClass) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, ParseException {
        SoapObject soapObject
                = (SoapObject)((SoapObject) ((SoapObject) response).getProperty(1)).getProperty(0);
        Constructor constructor = outputClass.getDeclaredConstructor(SoapObject.class);

        // SoapObject를 원하는 POJO type response로 안전하게 변환함.
        return (E)constructor.newInstance(soapObject);
    }
}

