package org.example.exception;

public class SQLErrorException extends RuntimeException {
    private Exception origin;

    public SQLErrorException(String message, Exception origin) {
        super(message); // SQLErrorException은 RuntimeException을 상속받음 고로, RuntimeException의 message를 담고있는 생성자를 호출함
        this.origin = origin;
    }// message는 예외가 발생했을 때 출력할 메시지를 저장하는 변수이며, origin은 예외 발생 원인에 대한 정보를 저장하는 변수입니다.

    public Exception getOrigin() { // 반환된 origin을 담고있는 getOrigin 메서드(Main클래스에서 예외처리용도로 호출됨, Exception에 있는 값을 불러오기때문에 Exception타입으로 선언됨.)
        return origin; // SQLErrorException에서 매개변수로 받아온 Exception origin이 클래스 인스턴스변수 origin에 담겨서 반환됨.
    }
}
