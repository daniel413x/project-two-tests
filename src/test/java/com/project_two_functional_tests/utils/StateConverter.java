package com.project_two_functional_tests.utils;

import java.util.HashMap;
import java.util.Map;

public class StateConverter {

    private static final Map<String, String> stateAbbreviationToNameMap = new HashMap<>();
    private static final Map<String, String> stateNameToAbbreviationMap = new HashMap<>();

    static {
        stateAbbreviationToNameMap.put("AL", "Alabama");
        stateAbbreviationToNameMap.put("AK", "Alaska");
        stateAbbreviationToNameMap.put("AZ", "Arizona");
        stateAbbreviationToNameMap.put("AR", "Arkansas");
        stateAbbreviationToNameMap.put("CA", "California");
        stateAbbreviationToNameMap.put("CO", "Colorado");
        stateAbbreviationToNameMap.put("CT", "Connecticut");
        stateAbbreviationToNameMap.put("DE", "Delaware");
        stateAbbreviationToNameMap.put("FL", "Florida");
        stateAbbreviationToNameMap.put("GA", "Georgia");
        stateAbbreviationToNameMap.put("HI", "Hawaii");
        stateAbbreviationToNameMap.put("ID", "Idaho");
        stateAbbreviationToNameMap.put("IL", "Illinois");
        stateAbbreviationToNameMap.put("IN", "Indiana");
        stateAbbreviationToNameMap.put("IA", "Iowa");
        stateAbbreviationToNameMap.put("KS", "Kansas");
        stateAbbreviationToNameMap.put("KY", "Kentucky");
        stateAbbreviationToNameMap.put("LA", "Louisiana");
        stateAbbreviationToNameMap.put("ME", "Maine");
        stateAbbreviationToNameMap.put("MD", "Maryland");
        stateAbbreviationToNameMap.put("MA", "Massachusetts");
        stateAbbreviationToNameMap.put("MI", "Michigan");
        stateAbbreviationToNameMap.put("MN", "Minnesota");
        stateAbbreviationToNameMap.put("MS", "Mississippi");
        stateAbbreviationToNameMap.put("MO", "Missouri");
        stateAbbreviationToNameMap.put("MT", "Montana");
        stateAbbreviationToNameMap.put("NE", "Nebraska");
        stateAbbreviationToNameMap.put("NV", "Nevada");
        stateAbbreviationToNameMap.put("NH", "New Hampshire");
        stateAbbreviationToNameMap.put("NJ", "New Jersey");
        stateAbbreviationToNameMap.put("NM", "New Mexico");
        stateAbbreviationToNameMap.put("NY", "New York");
        stateAbbreviationToNameMap.put("NC", "North Carolina");
        stateAbbreviationToNameMap.put("ND", "North Dakota");
        stateAbbreviationToNameMap.put("OH", "Ohio");
        stateAbbreviationToNameMap.put("OK", "Oklahoma");
        stateAbbreviationToNameMap.put("OR", "Oregon");
        stateAbbreviationToNameMap.put("PA", "Pennsylvania");
        stateAbbreviationToNameMap.put("RI", "Rhode Island");
        stateAbbreviationToNameMap.put("SC", "South Carolina");
        stateAbbreviationToNameMap.put("SD", "South Dakota");
        stateAbbreviationToNameMap.put("TN", "Tennessee");
        stateAbbreviationToNameMap.put("TX", "Texas");
        stateAbbreviationToNameMap.put("UT", "Utah");
        stateAbbreviationToNameMap.put("VT", "Vermont");
        stateAbbreviationToNameMap.put("VA", "Virginia");
        stateAbbreviationToNameMap.put("WA", "Washington");
        stateAbbreviationToNameMap.put("WV", "West Virginia");
        stateAbbreviationToNameMap.put("WI", "Wisconsin");
        stateAbbreviationToNameMap.put("WY", "Wyoming");
        stateAbbreviationToNameMap.put("DC", "District of Columbia");

        for (Map.Entry<String, String> entry : stateAbbreviationToNameMap.entrySet()) {
            stateNameToAbbreviationMap.put(entry.getValue().toUpperCase(), entry.getKey());
        }
    }

    public static String getStateName(String abbreviation) {
        return stateAbbreviationToNameMap.get(abbreviation.toUpperCase());
    }

    public static String getStateAbbreviation(String name) {
        return stateNameToAbbreviationMap.get(name.toUpperCase());
    }

    public static void main(String[] args) {
        String fullName = getStateName("CA");
        System.out.println("Full Name: " + fullName);

        String stateAbbr = getStateAbbreviation("California");
        System.out.println("Abbreviation: " + stateAbbr);
    }
}
