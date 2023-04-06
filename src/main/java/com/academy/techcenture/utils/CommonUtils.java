package com.academy.techcenture.utils;

public class CommonUtils {

    //We will hide the constructor from the pirotes

    private  CommonUtils(){
    }

    //create static methods
//this method will generate random string with a length equal to the length entered in the argument.
//the argument will be decided at the time of the usage
//returns string
    public static String generateRandomString(int length){

        String result = "";
        String source = "abcdefghejklmnopqrstuvwxyz";
        for(int i =0; i<=source.length(); i++){
            int randomIndex = (int) (Math.random() * source.length());
            char randomChar = source.charAt(randomIndex);
            String stringChar = Character.toString(randomChar);
            result = result.concat(stringChar);

        }
        return result;
    }

    //this method will generate random string with a length equal to the length entered in the argument
//the second argument is used to convert the returned string to uppercase
//the argument will be decided at the time of the usage
// the uppercase argument if it is true convert to UpperCase
    public static String generateRandomString(int length, boolean isUpperCase) {

        String result = "";
        String source = "abcdefghejklmnopqrstuvwxyz";
        for (int i = 0; i <= source.length(); i++) {
            int randomIndex = (int) (Math.random() * source.length());
            char randomChar = source.charAt(randomIndex);
            String stringChar = Character.toString(randomChar);
            result = result.concat(stringChar);

        }
        return isUpperCase ? result.toUpperCase() : result;

    }

    public static int generateRandomNumber(int min, int max){

        int random = (int)(Math.random()*((max - min)+1))+ min;

        return random;
    }

    //This method will generate random string Number up to defined limit
    public static String generateRandomStringNumber(int limit){

        String result = "";
        for (int i = 0; i<=limit; i++) {
            int random = generateRandomNumber(0, 9);
            result = result + random;

        }
        return result;
    }

}
