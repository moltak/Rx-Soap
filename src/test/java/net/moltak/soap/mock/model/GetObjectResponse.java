package net.moltak.soap.mock.model;

import net.moltak.soap.annotations.JSoapResField;

/**
 * Created by moltak on 15. 6. 12..
 */
public class GetObjectResponse {
    @JSoapResField(name = "name")
    private String name;

    @JSoapResField(name = "gender")
    private String gender;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
