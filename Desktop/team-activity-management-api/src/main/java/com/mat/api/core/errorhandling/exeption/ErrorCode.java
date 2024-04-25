package com.mat.api.core.errorhandling.exeption;

import com.mat.api.core.errorhandling.businessexeption.BusinessErrorCode;
import lombok.Getter;


@Getter
public enum ErrorCode implements BusinessErrorCode {

    PROFILE_NOT_EXIST("Profile not exist"),
    UUID_UNDEFINED("uuid not found"),
    ID_MUST_NOT_NULL("id must not be null"),
    MUST_NOT_NULL("must not be null"),
    FILE_IS_MISSING("file is missing"),
    FILENAME_IS_MISSING("file name is missing"),
    ERROR_UPLOADING_FILE("Error uploading file"),
    ERROR_COUNTING_FILES("Error counting files in the folder"),
    INVALID_FORMAT("The published message format is invalid."),
    SAME_FIELDS_IS_NULL("it is missing a required field."),
    ALREADY_AUTHENTICATED("this user is already authenticated."),
    INVALID_MESSAGE("The published message is not valid; it is missing a required attribute."),
    LOGIN_OR_PASSWORD_MUST_BE_NOT_NULL("Login or password must not be null"),
    PASSWORD_AND_CONFIRM_PASSWORD_NOT_EQUALS("Password and confirm passwords not equal"),
    SERVER_UNAVAILABLE("The destination server is either unavailable or incorrect."),
    PRIVATE_KEY_NOT_EXIST("The private key not exist"),
    RSA_ALGORITHM_NOT_FOUND("The RSA Algorithm not found"),
    INVALID_PRIVATE_KEY("The Private key is invalid for generation");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }

}
