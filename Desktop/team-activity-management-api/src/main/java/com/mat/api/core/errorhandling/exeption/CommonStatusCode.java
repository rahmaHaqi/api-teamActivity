package com.mat.api.core.errorhandling.exeption;

import com.mat.api.core.errorhandling.businessexeption.BusinessErrorCode;
import lombok.Getter;

@Getter
public enum CommonStatusCode implements BusinessErrorCode {
    SUCCESS("success"),
    INVALID_DATA( "data format not valid"),
    INCORRECT_CREDENTIALS( "Username / Password incorrect , Please try again"),
    JSON_PARSING_ERROR( "Error parsing JSON"),
    DATE_PARSING_ERROR( "Error parsing input date"),
    INVALID_DATE_FORMAT( "Error formatting date"),
    INVALID_PAYLOAD( "Invalid payload"),
    FIELD_REQUIRED( "field required"),
    CIPHER_TEXT_IS_NOT_CORRECT( "The cipherText is not correct"),
    INVALID_CIPHER_TEXT("Invalid CipherText"),
    CONFIG_NOT_FOUND( "Configuration file not found"),
    INTERNAL_EXCEPTION( "Internal Error"),
    ENTITY_IS_MISSING( "Entity is missing"),
    ID_IS_MISSING( "Id is missing"),
    ENTITY_NOT_FOUND( " not found"),
    ENTITY_NOT_COMPATIBLE_WITH_ID( "entity to update is not compatible with the id"),
    INVALID_PAYLOAD_EXCEPTION( "Invalid Payload exception"),
    INTEGRITY_CONSTRAINT_VIOLATION( "Integrity Constraint Violation exception"),
    NO_CONTENT_EXCEPTION( "No content exception"),
    EXCEPTION_RETRY( "exception please retry"),
    EMAIL_IS_MISSING( "email is missing"),
    PASSWORD_IS_MISSING( "password is missing"),
    UNHANDLED_EXCEPTION( "unhandled exception");



    private final String message;

    CommonStatusCode(final String message) {
        this.message = message;
    }

}
