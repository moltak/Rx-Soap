package net.moltak.soap.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by moltak on 15. 2. 5..
 */
public interface BaseRequest {
    public String getRequestName();
    public Map<String, String> getParams() throws IllegalAccessException, InvocationTargetException;
}
