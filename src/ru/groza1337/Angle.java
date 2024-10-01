package ru.groza1337;

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

    /* =========================== Операции ============================== */

    /**
     * Преобразует угол из радианов в градусы и проверяет его на диапазон [-360, 360].
     * @param angleInRadians Угол в радианах.
     * @return Угол в градусах.
     */
    private static double fromRadians(double angleInRadians) {
        double degrees = angleInRadians * 180 / Math.PI;
        if (!isValidDegrees(degrees)) {
            throw new IllegalArgumentException("Угол должен быть в диапазоне [-360, 360] градусов.");
        }
        return degrees;
    }

    /**
     * Преобразует угол из градусов в радианы и проверяет его на диапазон [-2π, 2π].
     * @param angleInDegrees Угол в градусах.
     * @return Угол в радианах.
     */
    private static double toRadians(double angleInDegrees) {
        if (!isValidDegrees(angleInDegrees)) {
            throw new IllegalArgumentException("Угол должен быть в диапазоне [-360, 360] градусов.");
        }
        return angleInDegrees * Math.PI / 180;
    }

    /**
     * Проверяет, находится ли угол в диапазоне [-360, 360] градусов.
     * @param angle Угол в градусах.
     * @return true, если угол в диапазоне, иначе false.
     */
    private static boolean isValidDegrees(double angle) {
        return angle >= -360 && angle <= 360;
    }

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

    /* ---------------------------- Порождение ---------------------------- */

    /**
     * Приватный конструктор для создания объекта угла.
     * @param angle Угол в градусах.
     */
    private Angle(double angle) {
        if (!isValidDegrees(angle)) {
            throw new IllegalArgumentException("Угол должен быть в диапазоне [-360, 360] градусов.");
        }
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

    /* --------------------- Арифметические операции ---------------------- */

    /**
     * Складывает текущий угол с переданным углом.
     * @param other Угол для сложения.
     * @return Новый угол как результат сложения.
     */
    @Override
    public Angle add(Angle other) {
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
    public Angle subtract(Angle other) {
        return new Angle(this._angle - other._angle);
    }

    /**
     * Вычитает текущий угол с переданным углом в радианах.
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
    public int compare(Angle other) {
        return Double.compare(this._angle, other._angle);
    }

    /**
     * Сравнивает текущий угол с переданным значением в радианах.
     * @param other Угол в радианах для сравнения.
     * @return 0 - если углы равны, -1 - если текущий угол меньше, 1 - если больше.
     */
    @Override
    public int compareWithRadians(double other) {
        return this.compare(radians(other));
    }

    /* --------------------- Определение типа угла ---------------------- */

    /**
     * Определяет тип угла на основе его значения в градусах.
     * @return Тип угла (острый, тупой, прямой и т.д.).
     */
    @Override
    public AngleType determineAngleType() {
        double degrees = this.getDegrees();
        if (degrees < 0) {
            degrees += 360;  // Учитываем отрицательные углы, делаем их положительными
        }

        if (degrees == 90) {
            return AngleType.RIGHT;       // Прямой угол
        } else if (degrees < 90) {
            return AngleType.ACUTE;       // Острый угол
        } else if (degrees == 180) {
            return AngleType.STRAIGHT;    // Развернутый угол
        } else if (degrees < 180) {
            return AngleType.OBTUSE;      // Тупой угол
        } else if (degrees == 360) {
            return AngleType.FULL;        // Полный круг
        } else {
            return AngleType.REFLEX;      // Рефлексный угол
        }
    }

    /* --------------------- Эквивалентность и хэш-код ---------------------- */

    /**
     * Проверяет эквивалентность двух углов.
     * @param other Другой объект для сравнения.
     * @return true, если углы равны, иначе false.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Angle)) {
            return false;
        }

        return this.compare((Angle) other) == 0;
    }

    /**
     * Возвращает хэш-код для данного угла.
     * @return Хэш-код угла на основе значения в градусах.
     */
    @Override
    public int hashCode() {
        return Double.hashCode(this._angle);
    }
}

