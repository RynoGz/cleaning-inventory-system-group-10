package za.belgiumcampus.cleaninginventory.util;

public class Validation {

    public static boolean isEmpty(String text){
        return text == null || text.trim().isEmpty();
    }

    public static boolean isPositiveInteger(String text){
        try{
            int number = Integer.parseInt(text);

            return number >= 0;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isValidEmail(String email){
        if(isEmpty(email)){
            return true;
        }
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(emailPattern);
    }

    public static boolean isValidPhoneNumber(String phone) {

        if (isEmpty(phone)) {
            return false;
        }

        String phonePattern = "^[0-9]{10}$";

        return phone.matches(phonePattern);
    }

    public static boolean isWithinLength(String text, int maxLength) {

        if (text == null) {
            return false;
        }

        return text.length() <= maxLength;
    }
}
