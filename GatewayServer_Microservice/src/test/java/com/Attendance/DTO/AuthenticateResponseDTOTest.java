package com.Attendance.DTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;

public class AuthenticateResponseDTOTest {

    @Test
    public void testBuildAuthenticateResponseDTO() {
        String accessToken = "sampleAccessToken";
        String refreshToken = "sampleRefreshToken";
        Collection<String> authorityList = Arrays.asList("READ", "WRITE");
        long expiresAt = 1234567890L;
        String userId = "sampleUserId";

        AuthenticateResponseDTO responseDTO = AuthenticateResponseDTO.build(
                accessToken,
                refreshToken,
                authorityList,
                expiresAt,
                userId
        );

        // Check if the responseDTO fields match the provided values
        Assertions.assertEquals(accessToken, responseDTO.getAccessToken());
        Assertions.assertEquals(refreshToken, responseDTO.getRefreshToken());
        Assertions.assertEquals(expiresAt, responseDTO.getExpiresAt());
        Assertions.assertEquals(userId, responseDTO.getUserId());

        // Check authorityList equality
        Assertions.assertIterableEquals(authorityList, responseDTO.getAuthorityList());
    }

    // You can write more test cases to cover edge cases, null inputs, etc.
}
