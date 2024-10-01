package ru.groza1337;
import lombok.NonNull;

/**
 * Угол.
 */
public class Angle implements AngleService{

    /* =========================== Свойства =============================== */

    /* ---------------------- Угол --------------------- */

    /**
     * Угол в градусах.
     */
    private final double _angle;

    /**
     * Представить в градусах.
     */
    @Override
    public double getDegrees() {
        return this._angle;
    }

    /**
     * Представить в радианах.
     */
    @Override
    public double getRadians() {
        return toRadians(this._angle);
    }

    /* =========================== Операции ============================== */

    /* --------------------- Операции преобразования ---------------------- */

    /**
     * Представить как строку.
     * @return Угол в градусах в формате строки
     */
    @Override
    public String toString() {
        return String.format("%.2f degrees", this._angle);
    }

    /**
     * Представить как строку.
     * @return Угол в радианах в формате строки
     */
    @Override
    public String toStringInRadians() {
        return String.format("%.2f radians", toRadians(this._angle));
    }

    /**
     * Возвращает хэш-код для данного угла.
     * Переопределение метода необходимо для поддержания согласованности с методом {@link #equals(Object)}.
     *
     * @return Хэш-код угла на основе значения в градусах.
     */
    @Override
    public int hashCode() {
        return Double.hashCode(this._angle);
    }

    /**
     * Перевод из радианов в градусы
     * @param angleInRadians угол в радианах
     * @return угол в градусах
     */
    private static double fromRadians(double angleInRadians) {
        return angleInRadians * 180 / Math.PI;
    }

    /**
     * Перевод из градусов в радианы
     * @param angleInDegrees угол в градусах
     * @return угол в радианах
     */
    private static double toRadians(double angleInDegrees) {
        return angleInDegrees * Math.PI / 180;
    }

    /* ---------------------------- Порождение ---------------------------- */

    /**
     * Порождение угла в градусах.
     * @param angle Угол в градусах
     */
    private Angle(double angle) {
        this._angle = angle;
    }

    /**
     * Порождение угла в градусах.
     * @param angle Угол в градусах
     */
    public static Angle degrees(double angle) {
        return new Angle(angle);
    }

    /**
     * Порождение угла в радианах.
     * @param angle Угол в радианах
     */
    public static Angle radians(double angle) {
        return new Angle(fromRadians(angle));
    }

    /* --------------------- Предопределение углов ---------------------- */

    /**
     * Угол в 0 градусов.
     * @return Угол в 0 градусов
     */
    public static Angle zero() {
        return Angle.degrees(0); // 0
    }

    /**
     * Угол в P радианах
     * @return угол в P радианах
     */
    public static Angle P() {
        return Angle.radians(Math.PI); // P
    }

    /* --------------------- Арифметические операции ---------------------- */

    /**
     * Сложение двух углов.
     * @param other второе слагаемое (Угол)
     * @return сумма двух углов
     */
    @Override
    public Angle add(@NonNull Angle other) {
        return new Angle(this._angle + other._angle);
    }

    /**
     * Сложение двух углов.
     * @param other второе слагаемое (угол в радианах)
     * @return сумма двух углов
     */
    @Override
    public Angle addRadians(double other) {
        return this.add(radians(other));
    }

    /**
     * Вычитание двух углов.
     * @param other вычитаемое (Угол)
     * @return разность двух углов
     */
    @Override
    public Angle subtract(@NonNull Angle other) {
        return new Angle(this._angle - other._angle);
    }

    /**
     * Вычитание двух углов.
     * @param other вычитаемое (угол в радианах)
     * @return разность двух углов
     */
    @Override
    public Angle subtractRadians(double other) {
        return this.subtract(radians(other));
    }

    /* --------------------- Операции сравнения ---------------------- */

    /** Сравнение двух углов.
     *
     * @param other другая Угол
     * @return 0 - углы равны, -1 - первый угол меньше второго, 1 - первый угол больше второго
     */
    @Override
    public int compare(@NonNull Angle other) {
        return Double.compare(this._angle, other._angle);
    }

    /** Сравнение углов с радианами.
     *
     * @param other другая Угол (в радианах)
     * @return 0 - углы равны, -1 - первый угол меньше второго, 1 - первый угол больше второго
     */
    @Override
    public int compareWithRadians(double other) {
        return Double.compare(this._angle, fromRadians(other));
    }

    /** Эквивалентность двух углов.
     *
     * @param other другой объект
     * @return эквивалентны ли объекты
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Angle)) {
            return false;
        }

        return this.compare((Angle) other) == 0;
    }
}
