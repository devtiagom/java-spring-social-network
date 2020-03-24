package com.myportfolio.socialnetwork.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserProfile {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private static final String INVALID_PROFILE_CODE = "Código inválido: ";

    private Integer profileCode;
    private String profileDescription;

    public static UserProfile toEnum(Integer profileCode) {
        if (profileCode == null) return null;

        for (UserProfile profile : UserProfile.values()) {
            if (profileCode.equals(profile.getProfileCode())) return profile;
        }

        throw new IllegalArgumentException(UserProfile.INVALID_PROFILE_CODE + profileCode);
    }
}
