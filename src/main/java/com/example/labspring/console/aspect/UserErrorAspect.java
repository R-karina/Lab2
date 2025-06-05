package com.example.labspring.console.aspect;

import com.example.labspring.console.error.UserInputException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserErrorAspect {
    //Аспект, который работает на методах с аннотацией HandleUserErrors (или классами) и предназначен для дефолтной обработки ошибок работы с пользователем
    //Если пользователь вводит неверные параметры, то перехватываем ошибку, безопасно обрабатываем ее и продолжаем работу
    @Around("@annotation(com.example.labspring.console.aspect.HandleUserErrors) || @within(com.example.labspring.console.aspect.HandleUserErrors)")
    public Object handleUserErrors(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (UserInputException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getLocalizedMessage());
            return null;
        }
    }
}
