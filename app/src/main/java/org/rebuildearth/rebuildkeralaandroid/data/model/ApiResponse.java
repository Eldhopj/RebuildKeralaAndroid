package org.rebuildearth.rebuildkeralaandroid.data.model;

public class ApiResponse {

    private Object response;
    private String error;

    public ApiResponse(Object response) {
        this.response = response;
        this.error = null;
    }

    public ApiResponse(String error) {
        this.error = error;
        this.response = null;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

