package com.example.labspring.console;

import com.example.labspring.console.aspect.ScannerRetry;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//Выносим логику работы со сканнером в отдельный бин чтобы создалась аспект прослойка с унифицированной работы с ошибками
@Component //Объявляем бином с дефолтным scope singleton, тк не нужен более чем в одном экземпляре
@ScannerRetry //Аннотация для аспекта
public class ScannerFacade {
    private final Scanner scanner;

    public ScannerFacade() {
        scanner = new Scanner(System.in);
    }

    @PreDestroy //Закрываем сканнер при остановке приложения
    public void destroy() {
        scanner.close();
    }

    public String getString() {
        String line = scanner.nextLine();
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Введите непустую строку.");
        }
        return line;
    }

    public Integer getInt() {
        String line = getString();
        return Integer.parseInt(line);
    }

    public Integer getPositiveInt() {
        Integer i = getInt();
        if (i <= 0) {
            throw new IllegalArgumentException("Введите положительное число.");
        }
        return i;
    }

    public Double getDouble() {
        String line = getString();
        return Double.parseDouble(line);
    }

    public Double getPositiveDouble() {
        Double d = getDouble();
        if (d <= 0) {
            throw new IllegalArgumentException("Введите положительное число.");
        }
        return d;
    }
}
