package ru.groza1337;

/**
 * Класс для работы с углами, реализующий интерфейс AngleService.
 * Хранит угол как в градусах, так и в радианах и предоставляет методы для операций с углами.
 */
public final class Angle implements AngleService {
    private final double angleInDegrees;
    private final double angleInRadians;

    // Предопределенные углы
    public static final Angle ZERO = new Angle(0); // Угол 0 градусов
    public static final Angle PI = new Angle(180); // Угол π радиан (180 градусов)

    /**
     * Проверяет, находится ли угол в градусах в пределах допустимого диапазона [-360, 360].
     * @param angleInDegrees Угол в градусах.
     * @return true, если угол допустим, иначе false.
     */
    private static boolean isValid(double angleInDegrees) {
        return angleInDegrees >= -360 && angleInDegrees <= 360;
    }

    /**
     * Конструктор, принимающий угол в градусах.
     * @param angleInDegrees Угол в градусах.
     * @throws IllegalArgumentException если угол выходит за допустимые пределы.
     */
    public Angle(double angleInDegrees) {
        if (isValid(angleInDegrees)) {
            this.angleInDegrees = angleInDegrees;
            this.angleInRadians = Math.toRadians(angleInDegrees);
        } else {
            throw new IllegalArgumentException("ru.groza1337.Angle must be between -360 and 360 degrees");
        }
    }

    /**
     * Конструктор, принимающий угол в радианах.
     * @param angleInRadians Угол в радианах.
     * @param isRadians Флаг для указания, что угол передан в радианах.
     * @throws IllegalArgumentException если угол выходит за допустимые пределы радиан.
     */
    public Angle(double angleInRadians, boolean isRadians) {
        if (isRadians) {
            if (isValid(Math.toDegrees(angleInRadians))) {
                this.angleInRadians = angleInRadians;
                this.angleInDegrees = Math.toDegrees(angleInRadians);
            } else {
                throw new IllegalArgumentException("ru.groza1337.Angle must be between -2π and 2π radians");
            }
        } else {
            throw new IllegalArgumentException("Use the primary constructor for degrees.");
        }
    }

    /**
     * Возвращает угол в градусах.
     * @return Угол в градусах.
     */
    @Override
    public double getAngleInDegrees() {
        return angleInDegrees;
    }

    /**
     * Возвращает угол в радианах.
     * @return Угол в радианах.
     */
    @Override
    public double getAngleInRadians() {
        return angleInRadians;
    }

    /**
     * Преобразует объект в строку с указанием угла в градусах и радианах.
     * @return Строковое представление угла.
     */
    @Override
    public String toString() {
        return String.format("Angle: %.2f degrees (%.2f radians)", angleInDegrees, angleInRadians);
    }

    /**
     * Определяет тип угла (острый, прямой, тупой, развернутый).
     * @return Тип угла.
     */
    @Override
    public TypeOfAngle determineTypeOfAngle() {
        double newAngleInDegrees;
        if (angleInDegrees > 180) {
            newAngleInDegrees = angleInDegrees - 360;
        } else {
            newAngleInDegrees = angleInDegrees;
        }
        if (newAngleInDegrees == 0) {
            return TypeOfAngle.Unfolded;
        }
        else if (newAngleInDegrees > -90 && newAngleInDegrees < 90) {
            return TypeOfAngle.Sharp;
        } else if (newAngleInDegrees == -90 || newAngleInDegrees == 90) {
            return TypeOfAngle.Square;
        } else if (newAngleInDegrees > -180 && newAngleInDegrees < 180) {
            return TypeOfAngle.Blunt;
        } else if (newAngleInDegrees == -180 || newAngleInDegrees == 180) {
            return TypeOfAngle.Unfolded;
        }
        return TypeOfAngle.Unfolded;
    }

    /**
     * Складывает текущий угол с переданным углом.
     * @param angle Угол для сложения.
     * @return Новый угол как результат сложения.
     */
    @Override
    public Angle add(Angle angle) {
        return new Angle(angleInDegrees + angle.angleInDegrees);
    }

    /**
     * Вычитает переданный угол из текущего угла.
     * @param angle Угол для вычитания.
     * @return Новый угол как результат вычитания.
     */
    @Override
    public Angle subtract(Angle angle) {
        return new Angle(angleInDegrees - angle.angleInDegrees);
    }

    /**
     * Сравнивает текущий угол с переданным углом.
     * @param angle Угол для сравнения.
     * @return -1 если текущий угол меньше, 0 если равен, и 1 если больше.
     */
    @Override
    public int compare(Angle angle) {
        return Double.compare(this.angleInDegrees, angle.getAngleInDegrees());
    }

    /**
     * Создает копию текущего угла.
     * @return Копия угла.
     */
    @Override
    public Angle copy() {
        return new Angle(angleInDegrees);
    }
}
