package ru.groza1337;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                В чем хотите задать 1 угол:
                1. В радианах
                2. В градусах""");
        int choice = sc.nextInt();  // Получаем выбор пользователя

        // Определяем, является ли ввод радианами
        boolean isInRadian;
        if (choice == 1) {
            isInRadian = true;
        } else if (choice == 2) {
            isInRadian = false;
        } else {
            throw new RuntimeException("Некорректный выбор.");
        }
        System.out.print("Введите значение угла: ");
        int answer1 = sc.nextInt();
        Angle angle1 = new Angle(answer1, isInRadian);

        System.out.println("""
                В чем хотите задать 2 угол:
                1. В радианах
                2. В градусах""");
        choice = sc.nextInt();  // Получаем выбор пользователя

        // Определяем, является ли ввод радианами
        if (choice == 1) {
            isInRadian = true;
        } else if (choice == 2) {
            isInRadian = false;
        } else {
            throw new RuntimeException("Некорректный выбор.");
        }
        System.out.print("Введите значение угла: ");
        int answer2 = sc.nextInt();
        Angle angle2 = new Angle(answer2, isInRadian);

        System.out.println(angle1);
        System.out.println(angle2);

    }
}
