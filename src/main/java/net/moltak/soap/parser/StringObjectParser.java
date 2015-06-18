package net.moltak.soap.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by moltak on 15. 2. 15..
 */
public class StringObjectParser<E> implements SoapParser {
    @SuppressWarnings("unchecked")
    @Override
    public Object parsing(Object response, Class outputClass) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, ParseException {
        Constructor constructor = outputClass.getConstructor(String.class);

        // SoapObject를 원하는 response로 안전하게 변환함.
        return (E)constructor.newInstance(response.toString());
    }
}