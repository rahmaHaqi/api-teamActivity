package com.mat.api.core.security.helpers;

public enum ClaimKeyEnum {
    CLAIM_KEY_USER_ID("id"),
    CLAIM_KEY_USERNAME("username"),
    CLAIM_KEY_CREATED("created"),
    CLAIM_KEY_NAME("name");
    private final String value;


    ClaimKeyEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
