package com.baozi.akka.task.utils;

public class HashUtil {

    /**
     * DJB哈希
     */
    public static int djbHash(String str) {
        if (null == str) {
            return 0;
        }
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * DEK哈希
     */
    public static int dekHash(String str) {
        if (null == str) {
            return 0;
        }
        int hash = str.length();
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

}
