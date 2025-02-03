package com.jh.tds.as.test;

import java.util.*;

public class GroupStrings {
    public static void main(String[] args) {
        // Input array
        String str[] = {"ABC", "BCA", "CBA", "ADO", "DAO", "OAD", "XYZ", "YZX"};
        
        // Create a map to store the sorted version of strings as keys and the grouped strings as values
        Map<String, List<String>> map = new HashMap<>();
        
        // Iterate over each string in the array
        for (String s : str) {
            // Sort the characters of the string
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            
            // Add the string to the corresponding group in the map
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(s);
        }
        
        // Print the results
        for (String key : map.keySet()) {
            List<String> group = map.get(key);
            System.out.println(String.join(",", group));
        }
    }
}
