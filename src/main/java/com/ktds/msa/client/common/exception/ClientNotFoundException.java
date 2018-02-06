package com.ktds.msa.client.common.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("애플리케이션을 찾을수 없습니다.");
    }

    public ClientNotFoundException(String clientId) {
        super( clientId + " 애플리케이션을 찾을수 없습니다.");
    }

}
