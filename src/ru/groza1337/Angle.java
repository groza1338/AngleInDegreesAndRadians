package ru.groza1337;

import lombok.NonNull;

/**
 * Класс для работы с углами, реализующий интерфейс AngleService.
 * Хранит угол в градусах и предоставляет методы для выполнения операций над углами.
 */
public class Angle implements AngleService {

    /* =========================== Свойства =============================== */

    /**
     * Значение угла в градусах.
     */
    private final double _angle;

    /**
     * Возвращает значение угла в градусах.
     * @return Угол в градусах.
     */
    @Override
    public double getDegrees() {
        return this._angle;
    }

    /**
     * Возвращает значение угла в радианах.
     * @return Угол в радианах.
     */
    @Override
    public double getRadians() {
        return toRadians(this._angle);
    }

    /* =========================== Операции ============================== */

    /**
     * Возвращает строковое представление угла в градусах.
     * @return Угол в градусах в формате строки.
     */
    @Override
    public String toString() {
        return String.format("%.2f degrees", this._angle);
    }

    /**
     * Возвращает строковое представление угла в радианах.
     * @return Угол в радианах в формате строки.
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
     * Преобразует угол из радиан в градусы.
     * @param angleInRadians Угол в радианах.
     * @return Угол в градусах.
     */
    private static double fromRadians(double angleInRadians) {
        return angleInRadians * 180 / Math.PI;
    }

    /**
     * Преобразует угол из градусов в радианы.
     * @param angleInDegrees Угол в градусах.
     * @return Угол в радианах.
     */
    private static double toRadians(double angleInDegrees) {
        return angleInDegrees * Math.PI / 180;
    }

    /* ---------------------------- Порождение ---------------------------- */

    /**
     * Приватный конструктор для создания объекта угла.
     * @param angle Угол в градусах.
     */
    private Angle(double angle) {
        this._angle = angle;
    }

    /**
     * Создает угол в градусах.
     * @param angle Угол в градусах.
     * @return Объект угла.
     */
    public static Angle degrees(double angle) {
        return new Angle(angle);
    }

    /**
     * Создает угол в радианах.
     * @param angle Угол в радианах.
     * @return Объект угла.
     */
    public static Angle radians(double angle) {
        return new Angle(fromRadians(angle));
    }

    /* --------------------- Предопределение углов ---------------------- */

    /**
     * Возвращает угол в 0 градусов.
     * @return Угол в 0 градусов.
     */
    public static Angle zero() {
        return Angle.degrees(0);
    }

    /**
     * Возвращает угол в PI радиан.
     * @return Угол в PI радиан.
     */
    public static Angle P() {
        return Angle.radians(Math.PI);
    }

    /* --------------------- Арифметические операции ---------------------- */

    /**
     * Складывает текущий угол с переданным углом.
     * @param other Угол для сложения.
     * @return Новый угол как результат сложения.
     */
    @Override
    public Angle add(@NonNull Angle other) {
        return new Angle(this._angle + other._angle);
    }

    /**
     * Складывает текущий угол с переданным углом в радианах.
     * @param other Угол в радианах для сложения.
     * @return Новый угол как результат сложения.
     */
    @Override
    public Angle addRadians(double other) {
        return this.add(radians(other));
    }

    /**
     * Вычитает переданный угол из текущего угла.
     * @param other Угол для вычитания.
     * @return Новый угол как результат вычитания.
     */
    @Override
    public Angle subtract(@NonNull Angle other) {
        return new Angle(this._angle - other._angle);
    }

    /**
     * Вычитает переданный угол в радианах из текущего угла.
     * @param other Угол в радианах для вычитания.
     * @return Новый угол как результат вычитания.
     */
    @Override
    public Angle subtractRadians(double other) {
        return this.subtract(radians(other));
    }

    /* --------------------- Операции сравнения ---------------------- */

    /**
     * Сравнивает текущий угол с переданным углом.
     * @param other Угол для сравнения.
     * @return 0 - если углы равны, -1 - если текущий угол меньше, 1 - если больше.
     */
    @Override
    public int compare(@NonNull Angle other) {
        return Double.compare(this._angle, other._angle);
    }

    /**
     * Сравнивает текущий угол с переданным углом в радианах.
     * @param other Угол в радианах для сравнения.
     * @return 0 - если углы равны, -1 - если текущий угол меньше, 1 - если больше.
     */
    @Override
    public int compareWithRadians(double other) {
        return Double.compare(this._angle, fromRadians(other));
    }

    /**
     * Проверяет эквивалентность двух углов.
     * @param other Объект для сравнения.
     * @return true, если углы эквивалентны; false в противном случае.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Angle)) {
            return false;
        }
        return this.compare((Angle) other) == 0;
    }
}