package mate.academy.myJdbc.web;

public class PasswordEncoder {
    public static String encode(String password) {
        char[] arr = password.toCharArray();
        char[] reverseArr = new char[arr.length];
        int y = arr.length-1;
        for (int x = 0; x < arr.length; x++) {
            reverseArr[x] = arr[y--];
        }
        String encodedPassword = "";
        for (char e : reverseArr)
            encodedPassword += e;
        return encodedPassword;
    }
}
