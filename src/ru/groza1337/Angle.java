package ru.groza1337;

/**
 * Класс для работы с углами, реализующий интерфейс AngleService.
 * Хранит угол как в градусах, так и в радианах и предоставляет методы для операций с углами.
 */
public final class Angle implements AngleService {
    private final double angleInDegrees;
    private final double angleInRadians;
    private final boolean isInRadians;

    // Предопределенные углы
    public static final Angle ZERO = new Angle(0, false); // Угол 0 градусов
    public static final Angle PI = new Angle(180, true); // Угол π радиан (180 градусов)

    /**
     * Проверяет, находится ли угол в градусах в пределах допустимого диапазона [-360, 360].
     * @param angleInDegrees Угол в градусах.
     * @return true, если угол допустим, иначе false.
     */
    private static boolean isValid(double angleInDegrees) {
        return angleInDegrees >= -360 && angleInDegrees <= 360;
    }

    /**
     * Конструктор, принимающий угол и флаг для указания, в чем он задан (в градусах или радианах).
     * @param angle Угол в радианах или градусах, в зависимости от значения флага isRadians.
     * @param isInRadians Флаг для указания, что угол передан в радианах. Если false, угол передан в градусах.
     * @throws IllegalArgumentException если угол выходит за допустимые пределы.
     */
    public Angle(double angle, boolean isInRadians) {
        if (isInRadians) {
            if (isValid(angle)) {
                this.angleInRadians = angle;
                this.angleInDegrees = Math.toDegrees(angle);
                this.isInRadians = true;
            } else {
                throw new IllegalArgumentException("Угол должен находиться в пределах [-2π, 2π] радиан.");
            }
        } else {
            if (isValid(angle)) {
                this.angleInDegrees = angle;
                this.angleInRadians = Math.toRadians(angle);
                this.isInRadians = false;
            } else {
                throw new IllegalArgumentException("Угол должен находиться в пределах [-360, 360] градусов.");
            }
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
        return new Angle(angleInDegrees + angle.angleInDegrees, isInRadians);
    }

    /**
     * Вычитает переданный угол из текущего угла.
     * @param angle Угол для вычитания.
     * @return Новый угол как результат вычитания.
     */
    @Override
    public Angle subtract(Angle angle) {
        return new Angle(angleInDegrees - angle.angleInDegrees, isInRadians);
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
        return new Angle(angleInDegrees, isInRadians);
    }
}
