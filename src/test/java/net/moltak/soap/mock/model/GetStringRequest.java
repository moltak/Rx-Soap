package net.moltak.soap.mock.model;

import net.moltak.soap.model.BaseRequest;
import net.moltak.soap.utility.ParameterBaker;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by moltak on 15. 6. 12..
 */
public class GetStringRequest implements BaseRequest {

    private final String name;

    public GetStringRequest(String name) {
        this.name = name;
    }

    @Override
    public String getRequestName() {
        return "get_string";
    }

    @Override
    public Map<String, String> getParams() throws IllegalAccessException, InvocationTargetException {
        return ParameterBaker.bake(this);
    }

    public String getName() {
        return name;
    }
}
