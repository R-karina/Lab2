package com.example.labspring.console.command;// Определяет интерфейс для команды, позволяя реализовать паттерн Command.

interface Command {
    void execute(Object... atrs);
}