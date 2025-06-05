package com.example.labspring.console.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScannerRetryAspect {

    // Обрабатывает методы, помеченные @ScannerRetry, или методы в классах, помеченных @ScannerRetry
    @Around("@annotation(com.example.labspring.console.aspect.ScannerRetry) || @within(com.example.labspring.console.aspect.ScannerRetry)")
    public Object handleUserErrors(ProceedingJoinPoint pjp) throws Throwable {
        while (true) {
            try {
                return pjp.proceed();
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода. Ожидалось число.");
                System.out.println("Повторите ввод.");
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный формат ввода. " + e.getMessage());
                System.out.println("Повторите ввод.");
            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка. " + e.getMessage());
                System.out.println("Повторите ввод.");
            }
        }
    }
}
