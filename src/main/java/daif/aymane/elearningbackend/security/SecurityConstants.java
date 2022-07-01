package daif.aymane.elearningbackend.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String SIGN_UP_URL = "/api/v1/signup";
    public static final String SIGN_IN_URL = "/api/v1/signin";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_SECRET = "04d1be62d7cc114e890796c269f23673c819fd0df6763ba89fc26535844f5740";
}
