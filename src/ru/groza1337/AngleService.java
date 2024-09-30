package ru.groza1337;

/**
 * Интерфейс для услуг по контракту, связанных с углами.
 * Определяет основные операции, которые должен поддерживать класс ru.groza1337.Angle.
 */
public interface AngleService {
    /**
     * Возвращает угол в градусах.
     * @return Угол в градусах.
     */
    double getAngleInDegrees();

    /**
     * Возвращает угол в радианах.
     * @return Угол в радианах.
     */
    double getAngleInRadians();

    /**
     * Складывает текущий угол с переданным углом.
     * @param angle Угол для сложения.
     * @return Новый угол как результат сложения.
     */
    Angle add(Angle angle);

    /**
     * Вычитает переданный угол из текущего угла.
     * @param angle Угол для вычитания.
     * @return Новый угол как результат вычитания.
     */
    Angle subtract(Angle angle);

    /**
     * Сравнивает текущий угол с переданным углом.
     * @param angle Угол для сравнения.
     * @return -1 если текущий угол меньше, 0 если равен, и 1 если больше.
     */
    int compare(Angle angle);

    /**
     * Создает копию текущего угла.
     * @return Копия угла.
     */
    Angle copy();

    /**
     * Определяет тип угла (острый, прямой, тупой, развернутый).
     * @return Тип угла.
     */
    TypeOfAngle determineTypeOfAngle();
}