package ru.groza1337;

public class Main {
    public static void main(String[] args) {
        Angle angle = Angle.degrees(180);
        Angle angle1 = Angle.radians(2 * Math.PI);
        System.out.println(angle);
        System.out.println(angle1);
        System.out.println(angle.addRadians(angle1.getRadians()));
    }
}
