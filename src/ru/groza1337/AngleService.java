package ru.groza1337;

import lombok.NonNull;

public interface AngleService {
    /**
     * Представить в градусах.
     */
    public double getDegrees();

    /**
     * Представить в радианах.
     */
    public double getRadians();

    /**
     * Представить как строку.
     * @return Угол в градусах в формате строки
     */
    public String toString();

    /**
     * Представить как строку.
     * @return Угол в радианах в формате строки
     */
    public String toStringInRadians();

    /**
     * Сложение двух углов.
     * @param other второе слагаемое (Угол)
     * @return сумма двух углов
     */
    public Angle add(@NonNull Angle other);

    /**
     * Сложение двух углов.
     * @param other второе слагаемое (угол в радианах)
     * @return сумма двух углов
     */
    public Angle addRadians(double other);

    /**
     * Вычитание двух углов.
     * @param other вычитаемое (Угол)
     * @return разность двух углов
     */
    public Angle subtract(@NonNull Angle other);

    /**
     * Вычитание двух углов.
     * @param other вычитаемое (угол в радианах)
     * @return разность двух углов
     */
    public Angle subtractRadians(double other);

    /** Сравнение двух углов.
     *
     * @param other другая Угол
     * @return 0 - углы равны, -1 - первый угол меньше второго, 1 - первый угол больше второго
     */
    public int compare(@NonNull Angle other);

    /** Сравнение углов с радианами.
     *
     * @param other другая Угол (в радианах)
     * @return 0 - углы равны, -1 - первый угол меньше второго, 1 - первый угол больше второго
     */
    public int compareWithRadians(double other);
}
