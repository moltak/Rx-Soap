package net.moltak.soap.parser;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by moltak on 15. 2. 11..
 */
public class DefaultSoapParser implements SoapParser {

    @Override
    public Object parsing(Object response, Class outputClass) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, ParseException {
        return response.toString();
    }
}
