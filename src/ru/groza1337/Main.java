package ru.groza1337;

public class Main {
    public static void main(String[] args) {
        Angle angle = new Angle(360);
        Angle angle1 = new Angle(45);
        System.out.println(angle.determineTypeOfAngle());
        System.out.println(angle.compare(angle1));
        Angle angle2 = angle1.copy();
        System.out.println(angle2.determineTypeOfAngle());
        System.out.println(angle);
    }
}
