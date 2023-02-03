package com.epam.taxi_service.utils.regex;

public class Regex {
    public static final String EMAIL_REGEX = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";

    public static final String NAME_REGEX = "^[A-Za-zА-ЩЮЯа-щьюяґіїє'\\- ]{1,30}";

    public static final String COMPLEX_NAME_REGEX = "^[\\wА-ЩЬЮЯҐІЇЄа-щьюяґіїє'.,;:+\\-~`!@#$^&*()={}| ]{2,70}";

    public static final String DESCRIPTION_REGEX = "^[\\wА-ЩЬЮЯҐІЇЄа-щьюяґіїє'.,;:+\\-~`!@#$^&*()={}| ]{1,200}";

    public static final String CAR_ADDRESS_REGEX = "^[A-Za-zА-ЯІЇа-яіЇ]+\\s*[A-Za-zА-ЯІЇа-яіЇ]*\\s*[A-Za-zА-ЯІЇа-яіЇ]*\\s{1}[\\d/]*\\s{1}[A-Za-zА-ЯІЇа-яіЇ]*$";
    private Regex() {}
}
