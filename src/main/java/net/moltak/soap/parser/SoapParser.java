package net.moltak.soap.parser;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by moltak on 15. 2. 10..
 */
public interface SoapParser<E> {
    public E parsing(Object response, Class outputClass) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, ParseException;
}
