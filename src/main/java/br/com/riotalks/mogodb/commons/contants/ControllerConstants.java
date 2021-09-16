package br.com.riotalks.mogodb.commons.contants;

public class ControllerConstants {
    // Versions
    public static final String V1 = "/v1";
    // Type
    public static final String USER = "/user";
    public static final String USER_BULK = "/user/bulk";
    public static final String USER_ID = "/{userId}";


    private ControllerConstants() {
        throw new IllegalStateException("Utility class");
    }

}
