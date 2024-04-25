package com.mat.api.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mat.api.core.errorhandling.businessexeption.BusinessErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody<T> {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @Builder
    public ResponseBody(final BusinessErrorCode status, final T data) {
        this.message = status.getMessage();
        this.data = data;
    }

    @Builder
    public ResponseBody(final BusinessErrorCode status, final String message) {
        this.message = message + " " + status.getMessage();
    }
}
