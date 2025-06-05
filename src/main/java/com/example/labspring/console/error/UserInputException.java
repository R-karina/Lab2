package com.example.labspring.console.error;

//Кастомное исключение для работы с пользователем для аспекта
public class UserInputException extends RuntimeException {
    public UserInputException(String message) {
        super(message);
    }
}
