package com.syh.uit.exception.response;

public class APIErrorResponse {
    private String error;
    private String error_description;

    public String getError() {
        return error;
    }

    public String getError_description() {
        return error_description;
    }

    public static final class ApiErrorResponseBuilder {
        private String error;
        private String error_description;

        public ApiErrorResponseBuilder() {
        }

        public ApiErrorResponseBuilder withError(String error) {
            this.error = error;
            return this;
        }

        public ApiErrorResponseBuilder withError_description(String error_description) {
            this.error_description = error_description;
            return this;
        }

        public APIErrorResponse build() {
            APIErrorResponse apiErrorResponse = new APIErrorResponse();
            apiErrorResponse.error = this.error;
            apiErrorResponse.error_description = this.error_description;
            return apiErrorResponse;
        }
    }
}
