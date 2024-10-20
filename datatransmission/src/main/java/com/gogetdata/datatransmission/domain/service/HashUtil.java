package com.gogetdata.datatransmission.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HashUtil {

    // 키셋을 추출하고 정렬하는 메서드
    public static List<String> extractAndSortKeys(JsonNode dataNode) {
        Iterator<String> fieldNames = dataNode.fieldNames();
        List<String> keys = new ArrayList<>();
        while (fieldNames.hasNext()) {
            keys.add(fieldNames.next());
        }
        Collections.sort(keys);
        return keys;
    }

    // 해시값을 생성하는 메서드
    public static String generateHash(List<String> sortedKeys) {
        try {
            String concatenatedKeys = String.join(",", sortedKeys);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(concatenatedKeys.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 알고리즘을 사용할 수 없습니다.", e);
        }
    }
}
