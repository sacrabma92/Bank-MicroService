package com.carlos.accounts.constants;

public class StaticResponseHttp {

    private StaticResponseHttp(){

    }

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "123 AV. Siempre Viva";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";

    public static final String STATUS_400 = "400";
    public static final String MESSAGE_400 = "Bad Request. The server could not understand the request due to invalid syntax.";

    public static final String STATUS_401 = "401";
    public static final String MESSAGE_401 = "Unauthorized. Authentication is required and has failed or has not yet been provided.";

    public static final String STATUS_403 = "403";
    public static final String MESSAGE_403 = "Access denied. You do not have the necessary permissions to access this resource.";

    public static final String STATUS_404 = "404";
    public static final String MESSAGE_404 = "Not Found. The requested resource could not be found on this server.";

    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dey team";

    public static final String STATUS_503 = "503";
    public static final String MESSAGE_503 = "Service Unavailable. The server is currently unable to handle the request due to temporary overloading or maintenance.";

}
