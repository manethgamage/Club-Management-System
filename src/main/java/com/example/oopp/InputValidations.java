package com.example.oopp;

public class InputValidations {
    // student & teacher ID validator
    public static boolean validateId(String id){
        if(id == null || id.isBlank()){
            return false;
        }
        if(id.trim().matches("\\s*S\\d{3}\\s*")){
            return true;
        }
        if(id.matches("\\s*T\\d{3}\\s*")){
            return true;
        }
        return false;
    }


    //check password and confirmPassword are equal

    public static boolean arePasswordsEqual(String password, String confirmPassword){
        if(password != null && confirmPassword != null){
            return password.equals(confirmPassword);
        }
        return false;
    }


}
