package com.CarSaleWebsite.Kolesa.Functions;


public class StringConfigurerFunctions {

    public static String replaceWhiteSpaceWithMinus(String word){
        return word.replaceAll("\\s+", "-");
    }
}
