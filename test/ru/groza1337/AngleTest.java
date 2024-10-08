package ru.groza1337;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AngleTest {

    // 1. **Конструкторы**: Проверка создания объекта с допустимыми и недопустимыми значениями
    @Nested
    class ConstructorTests {

        /**
         * Тесты для проверки создания объектов с допустимыми значениями в градусах.
         */
        @ParameterizedTest(name = "Создание угла с допустимым значением в градусах: {0}")
        @CsvSource({
                "0, 'Zero angle'",
                "180, 'Straight angle'",
                "-180, 'Negative straight angle'",
                "360, 'Full rotation angle'",
                "-360, 'Negative full rotation angle'"
        })
        void testAngleCreation_validDegrees(double degrees) {
            Angle.degrees(degrees);
        }

        /**
         * Тесты для проверки создания объектов с допустимыми значениями в радианах.
         */
        @ParameterizedTest(name = "Создание угла с допустимым значением в радианах: {0}")
        @CsvSource({
                "0, 'Zero angle'",
                "3.14159, 'Pi radians'",
                "-3.14159, 'Negative Pi radians'",
                "6.28318, 'Full rotation in radians'",
                "-6.28318, 'Negative full rotation in radians'"
        })
        void testAngleCreation_validRadians(double radians) {
            Angle.radians(radians);
        }

        /**
         * Тесты для проверки создания объектов с недопустимыми значениями за пределами диапазона.
         * Проверяем, что выбрасывается IllegalArgumentException.
         */
        @ParameterizedTest(name = "{1}")
        @CsvSource({
                "370, 'Angle degrees out of bounds (370 degrees)'",
                "-370, 'Angle degrees out of bounds (-370 degrees)'"
        })
        void testAngleCreation_outOfBounds(double input, String description) {
            Executable executable = () -> Angle.degrees(input);
            assertThrows(IllegalArgumentException.class, executable, description);
        }

        /**
         * Тесты для проверки создания объектов с недопустимыми значениями в радианах.
         * Проверяем, что выбрасывается IllegalArgumentException.
         */
        @ParameterizedTest(name = "{1}")
        @CsvSource({
                "6.3, 'Angle radians out of bounds (6.3 radians)'",
                "-6.3, 'Angle radians out of bounds (-6.3 radians)'",
        })
        void testAngleCreation_outOfBoundsRadians(double radians, String description) {
            Executable executable = () -> Angle.radians(radians);
            assertThrows(IllegalArgumentException.class, executable, description);
        }
    }

    // 2. **Геттеры и сеттеры**
    @Nested
    class PropertyAccessTests {

        /**
         * Тест для метода getDegrees. Проверяем, что значение в градусах корректно возвращается.
         */
        @ParameterizedTest(name = "Проверка метода getDegrees для угла {0} градусов")
        @CsvSource({
                "0, 0",
                "90, 90",
                "180, 180",
                "-180, -180",
                "360, 360",
                "-360, -360"
        })
        void testGetDegrees(double inputDegrees, double expectedDegrees) {
            Angle angle = Angle.degrees(inputDegrees);
            assertEquals(expectedDegrees, angle.getDegrees(),
                    "Метод getDegrees должен возвращать корректное значение угла в градусах");
        }

        /**
         * Тест для метода getRadians. Проверяем, что значение корректно конвертируется из градусов в радианы.
         */
        @ParameterizedTest(name = "Проверка метода getRadians для угла {0} градусов")
        @CsvSource({
                "0, 0",
                "180, 3.14159",
                "-180, -3.14159",
                "360, 6.28318",
                "-360, -6.28318"
        })
        void testGetRadians(double inputDegrees, double expectedRadians) {
            Angle angle = Angle.degrees(inputDegrees);
            assertEquals(expectedRadians, angle.getRadians(), 0.00001,
                    "Метод getRadians должен корректно преобразовывать угол из градусов в радианы");
        }

        /**
         * Тесты для метода getDegrees на значения, выходящие за пределы диапазона [-360, 360].
         * Ожидаем IllegalArgumentException.
         */
        @ParameterizedTest(name = "{1}")
        @CsvSource({
                "370, 'Angle degrees out of bounds (370 degrees)'",
                "-370, 'Angle degrees out of bounds (-370 degrees)'"
        })
        void testGetDegrees_OutOfBounds(double inputDegrees, String description) {
            assertThrows(IllegalArgumentException.class, () -> Angle.degrees(inputDegrees),
                    description);
        }

        /**
         * Тесты для метода getRadians на значения, выходящие за пределы диапазона [-2π, 2π].
         * Ожидаем IllegalArgumentException.
         */
        @ParameterizedTest(name = "{1}")
        @CsvSource({
                "6.3, 'Angle radians out of bounds (6.3 radians)'",
                "-6.3, 'Angle radians out of bounds (-6.3 radians)'"
        })
        void testGetRadians_OutOfBounds(double inputRadians, String description) {
            assertThrows(IllegalArgumentException.class, () -> Angle.radians(inputRadians),
                    description);
        }
    }

    @Nested
    class StringConversionTests {

        /**
         * 3. Тест для метода toString, который возвращает строку угла в градусах с двумя знаками после запятой.
         */
        @ParameterizedTest(name = "Проверка метода toString для угла {0} градусов")
        @CsvSource({
                "0, '0.00 degrees'",
                "90, '90.00 degrees'",
                "180, '180.00 degrees'",
                "-180, '-180.00 degrees'",
                "360, '360.00 degrees'",
                "-360, '-360.00 degrees'",
                "45.678, '45.68 degrees'",
                "-123.456, '-123.46 degrees'"
        })
        void testToString(double degrees, String expectedString) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(expectedString, angle.toString(),
                    "Метод toString должен возвращать корректное строковое представление угла в градусах");
        }

        /**
         * Тест для метода toStringInRadians, который возвращает строку угла в радианах с двумя знаками после запятой.
         */
        @ParameterizedTest(name = "Проверка метода toStringInRadians для угла {0} градусов")
        @CsvSource({
                "0, '0.00 radians'",
                "180, '3.14 radians'",
                "-180, '-3.14 radians'",
                "360, '6.28 radians'",
                "-360, '-6.28 radians'",
                "45, '0.79 radians'",
                "-45, '-0.79 radians'"
        })
        void testToStringInRadians(double degrees, String expectedString) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(expectedString, angle.toStringInRadians(),
                    "Метод toStringInRadians должен возвращать корректное строковое представление угла в радианах");
        }
    }

    // 4. **Сложение**: Проверка сложения углов в градусах и радианах, особенно при переходе через границу диапазона
    @Nested
    class AdditionTests {

        @ParameterizedTest(name = "Сложение углов в градусах: {0} + {1}")
        @CsvSource({
                "180, 180, 360, 'Добавление двух положительных углов с результатом на границе диапазона'",
                "0, 0, 0, 'Сложение нулевых углов'",
                "-180, 180, 0, 'Сложение углов с противоположными знаками'",
                "0, -180, -180, 'Добавление отрицательного угла'",
                "-180, -180, -360, 'Добавление двух отрицательных углов'",
                "360, 1, 'Exception', 'Сложение углов за пределами диапазона (ожидаем IllegalArgumentException)'"
        })
        void testAdditionDegrees(double angle1, double angle2, String expected, String description) {
            Angle a1 = Angle.degrees(angle1);
            Angle a2 = Angle.degrees(angle2);
            if (expected.equals("Exception")) {
                assertThrows(IllegalArgumentException.class, () -> a1.add(a2), description);
            } else {
                Angle result = a1.add(a2);
                assertEquals(Double.parseDouble(expected), result.getDegrees(), 0.01, description);
            }
        }

        @ParameterizedTest(name = "Сложение углов в радианах: {0} + {1}")
        @CsvSource({
                "3.14159, 3.14159, 6.28318, 'Сложение двух положительных углов в радианах'",
                "0, 0, 0, 'Сложение нулевых углов в радианах'",
                "-3.14159, 3.14159, 0, 'Сложение углов с противоположными знаками в радианах'",
                "0, -3.14159, -3.14159, 'Добавление отрицательного угла в радианах'",
                "-3.14159, -3.14159, -6.28318, 'Добавление двух отрицательных углов в радианах'",
                "6.28318, 0.1, 'Exception', 'Сложение углов за пределами диапазона (ожидаем IllegalArgumentException)'"
        })
        void testAdditionRadians(double angle1, double angle2, String expected, String description) {
            Angle a1 = Angle.radians(angle1);
            if (expected.equals("Exception")) {
                assertThrows(IllegalArgumentException.class, () -> a1.addRadians(angle2), description);
            } else {
                Angle result = a1.addRadians(angle2);
                assertEquals(Double.parseDouble(expected), result.getRadians(), 0.01, description);
            }
        }
    }

    // 5. **Вычитание**: Проверка вычитания углов в градусах и радианах, особенно при переходе через границу диапазона
    @Nested
    class SubtractionTests {

        @ParameterizedTest(name = "Вычитание углов в градусах: {0} - {1}")
        @CsvSource({
                "180, 180, 0, 'Вычитание одинаковых углов, результат — 0'",
                "360, 180, 180, 'Вычитание углов с результатом на границе диапазона'",
                "0, -180, 180, 'Вычитание отрицательного угла, получаем положительный угол'",
                "-180, -180, 0, 'Вычитание двух отрицательных углов с равными значениями'",
                "-360, 1, 'Exception', 'Вычитание углов за пределами диапазона (ожидаем IllegalArgumentException)'"
        })
        void testSubtractionDegrees(double angle1, double angle2, String expected, String description) {
            Angle a1 = Angle.degrees(angle1);
            Angle a2 = Angle.degrees(angle2);
            if (expected.equals("Exception")) {
                assertThrows(IllegalArgumentException.class, () -> a1.subtract(a2), description);
            } else {
                Angle result = a1.subtract(a2);
                assertEquals(Double.parseDouble(expected), result.getDegrees(), 0.01, description);
            }
        }

        @ParameterizedTest(name = "Вычитание углов в радианах: {0} - {1}")
        @CsvSource({
                "3.14159, 3.14159, 0, 'Вычитание одинаковых углов в радианах, результат — 0'",
                "6.28318, 3.14159, 3.14159, 'Вычитание углов с результатом на границе диапазона'",
                "0, -3.14159, 3.14159, 'Вычитание отрицательного угла, получаем положительный угол'",
                "-3.14159, -3.14159, 0, 'Вычитание двух отрицательных углов с равными значениями'",
                "-6.28318, 0.1, 'Exception', 'Вычитание углов за пределами диапазона (ожидаем IllegalArgumentException)'"
        })
        void testSubtractionRadians(double angle1, double angle2, String expected, String description) {
            Angle a1 = Angle.radians(angle1);
            if (expected.equals("Exception")) {
                assertThrows(IllegalArgumentException.class, () -> a1.subtractRadians(angle2), description);
            } else {
                Angle result = a1.subtractRadians(angle2);
                assertEquals(Double.parseDouble(expected), result.getRadians(), 0.01, description);
            }
        }
    }

    // 6. **Сравнение углов**: Проверка метода compare для сравнения углов
    @Nested
    class ComparisonTests {

        /**
         * Тесты для проверки равенства углов в градусах.
         */
        @ParameterizedTest(name = "Сравнение углов в градусах: {0} и {1}")
        @CsvSource({
                "0, 0, 0, 'Сравнение нулевых углов, результат — равны'",
                "180, 180, 0, 'Сравнение одинаковых положительных углов, результат — равны'",
                "-180, -180, 0, 'Сравнение одинаковых отрицательных углов, результат — равны'",
                "180, -180, 1, 'Сравнение 180 и -180, результат — первый угол больше'",
                "-360, 360, -1, 'Сравнение -360 и 360, результат — второй угол больше'",
                "0, 360, -1, 'Сравнение угла 0 и угла на границе 360'"
        })
        void testComparisonDegrees(double angle1, double angle2, int expected, String description) {
            Angle a1 = Angle.degrees(angle1);
            Angle a2 = Angle.degrees(angle2);
            assertEquals(expected, a1.compare(a2), description);
        }

        /**
         * Тесты для проверки равенства углов в радианах.
         */
        @ParameterizedTest(name = "Сравнение углов в радианах: {0} и {1}")
        @CsvSource({
                "0, 0, 0, 'Сравнение нулевых углов, результат — равны'",
                "3.14159, 3.14159, 0, 'Сравнение одинаковых углов π, результат — равны'",
                "-3.14159, -3.14159, 0, 'Сравнение одинаковых отрицательных углов -π, результат — равны'",
                "3.14159, -3.14159, 1, 'Сравнение π и -π, результат — первый угол больше'",
                "-6.28318, 6.28318, -1, 'Сравнение -2π и 2π, результат — второй угол больше'",
                "0, 6.28318, -1, 'Сравнение угла 0 и угла на границе 2π'"
        })
        void testComparisonRadians(double angle1, double angle2, int expected, String description) {
            Angle a1 = Angle.radians(angle1);
            Angle a2 = Angle.radians(angle2);
            assertEquals(expected, a1.compare(a2), description);
        }

        /**
         * Тесты для метода compareWithRadians.
         */
        @ParameterizedTest(name = "Сравнение углов с радианами: {0} и {1}")
        @CsvSource({
                "0, 0, 0, 'Сравнение нулевых углов, результат — равны'",
                "180, 3.141592653589793, 0, 'Сравнение 180° и π радиан, результат — равны'",
                "-180, -3.141592653589793, 0, 'Сравнение -180° и -π радиан, результат — равны'",
                "180, -3.14159, 1, 'Сравнение 180° и -π радиан, результат — первый угол больше'",
                "-360, 6.28318, -1, 'Сравнение -360° и 2π радиан, результат — второй угол больше'",
                "0, 6.28318, -1, 'Сравнение угла 0 и угла на границе 2π радиан'"
        })
        void testCompareWithRadians(double degrees, double radians, int expected, String description) {
            Angle angleDegrees = Angle.degrees(degrees);
            assertEquals(expected, angleDegrees.compareWithRadians(radians), description);
        }
    }

    // 7. **Тип угла**: Проверка метода determineAngleType для определения типа угла
    @Nested
    class DetermineAngleTypeTests {

        /**
         * Тесты для проверки определения острого угла.
         */
        @ParameterizedTest(name = "Угол {0}° должен быть острым")
        @CsvSource({
                "0, 'Острый угол - 0°'",
                "45, 'Острый угол - 45°'",
                "89.9, 'Угол чуть меньше 90° - острый'"
        })
        void testAcuteAngle(double degrees, String description) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(AngleType.ACUTE, angle.determineAngleType(), description);
        }

        /**
         * Тесты для проверки определения прямого угла.
         */
        @ParameterizedTest(name = "Угол {0}° должен быть прямым")
        @CsvSource({
                "90, 'Прямой угол - 90°'"
        })
        void testRightAngle(double degrees, String description) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(AngleType.RIGHT, angle.determineAngleType(), description);
        }

        /**
         * Тесты для проверки определения тупого угла.
         */
        @ParameterizedTest(name = "Угол {0}° должен быть тупым")
        @CsvSource({
                "91, 'Угол чуть больше 90° - тупой'",
                "135, 'Тупой угол - 135°'",
                "179.9, 'Угол чуть меньше 180° - тупой'"
        })
        void testObtuseAngle(double degrees, String description) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(AngleType.OBTUSE, angle.determineAngleType(), description);
        }

        /**
         * Тесты для проверки определения развернутого угла.
         */
        @ParameterizedTest(name = "Угол {0}° должен быть развернутым")
        @CsvSource({
                "180, 'Развернутый угол - 180°'"
        })
        void testStraightAngle(double degrees, String description) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(AngleType.STRAIGHT, angle.determineAngleType(), description);
        }

        /**
         * Тесты для проверки определения рефлексного угла.
         */
        @ParameterizedTest(name = "Угол {0}° должен быть рефлексным")
        @CsvSource({
                "181, 'Угол чуть больше 180° - рефлексный'",
                "270, 'Рефлексный угол - 270°'",
                "359.9, 'Угол чуть меньше 360° - рефлексный'"
        })
        void testReflexAngle(double degrees, String description) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(AngleType.REFLEX, angle.determineAngleType(), description);
        }

        /**
         * Тесты для проверки определения полного угла.
         */
        @ParameterizedTest(name = "Угол {0}° должен быть полным кругом")
        @CsvSource({
                "360, 'Полный угол - 360°'"
        })
        void testFullAngle(double degrees, String description) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(AngleType.FULL, angle.determineAngleType(), description);
        }

        /**
         * Тесты для проверки углов в радианах, которые также должны определять правильный тип.
         */
        @ParameterizedTest(name = "Угол {0} радиан должен определяться как {1}")
        @CsvSource({
                "0, ACUTE, '0 радиан - острый'",
                "1.5707963267948966, RIGHT, 'π/2 радиан - прямой'",
                "3.141592653589793, STRAIGHT, 'π радиан - развернутый'",
                "4.71238898038469, REFLEX, '3π/2 радиан - рефлексный'",
                "6.283185307179586, FULL, '2π радиан - полный круг'"
        })
        void testAngleTypeRadians(double radians, AngleType expectedType, String description) {
            Angle angle = Angle.radians(radians);
            assertEquals(expectedType, angle.determineAngleType(), description);
        }
    }

    // 8. **Создание копии объекта**: Проверка на равенство копий, иммутабельность и совпадение hashCode
    @Nested
    class CopyTests {

        /**
         * Тест для проверки, что копия объекта равна оригиналу.
         */
        @Test
        void testCopyEqualsOriginal() {
            Angle original = Angle.degrees(45);
            Angle copy = Angle.degrees(original.getDegrees());
            assertEquals(original, copy, "Копия должна быть равна оригиналу.");
        }

        /**
         * Тест для проверки, что изменения в одном экземпляре не влияют на другой.
         * В данном случае проверяется неизменяемость класса.
         */
        @Test
        void testImmutability() {
            Angle original = Angle.degrees(90);
            Angle copy = original.add(Angle.degrees(45));
            assertNotEquals(original, copy, "Оригинал и измененная копия не должны быть равны.");
            assertEquals(90, original.getDegrees(), "Оригинальный объект должен оставаться неизменным.");
        }

        /**
         * Тест для проверки, что у копий с одинаковыми углами одинаковый hashCode.
         */
        @Test
        void testHashCodeForEqualAngles() {
            Angle angle1 = Angle.degrees(180);
            Angle angle2 = Angle.degrees(180);
            assertEquals(angle1.hashCode(), angle2.hashCode(), "Углы с одинаковым значением должны иметь одинаковый hashCode.");
        }

        /**
         * Тест для проверки равенства копий, созданных в радианах.
         */
        @Test
        void testCopyEqualsOriginalInRadians() {
            Angle original = Angle.radians(Math.PI / 2);
            Angle copy = Angle.radians(original.getRadians());
            assertEquals(original, copy, "Копия, созданная в радианах, должна быть равна оригиналу.");
        }
    }

    @Nested
    class AngleContractTests {

        /**
         * Тест для проверки, что класс Angle реализует все методы интерфейса AngleService.
         * Этот тест проверяет, что методы корректно возвращают значения.
         */
        @Test
        void testInterfaceImplementation() {
            Angle angle = Angle.degrees(45);

            // Проверка методов получения угла
            assertEquals(45, angle.getDegrees(), 0.0001, "Метод getDegrees() должен возвращать корректное значение угла.");
            assertEquals(Math.PI / 4, angle.getRadians(), 0.0001, "Метод getRadians() должен возвращать угол, конвертированный в радианы.");

            // Проверка методов преобразования в строку
            assertEquals("45.00 degrees", angle.toString(), "Метод toString() должен возвращать угол в градусах в формате строки.");
            assertEquals("0.79 radians", angle.toStringInRadians(), "Метод toStringInRadians() должен возвращать угол в радианах в формате строки.");
        }

        /**
         * Тест для проверки иммутабельности класса Angle: метод add() должен возвращать новый экземпляр.
         */
        @Test
        void testAddMethodReturnsNewInstance() {
            Angle angle1 = Angle.degrees(90);
            Angle angle2 = Angle.degrees(30);
            Angle result = angle1.add(angle2);

            // Проверка, что возвращен новый экземпляр, а исходный объект не изменен
            assertNotSame(angle1, result, "Метод add() должен возвращать новый экземпляр, а не изменять исходный объект.");
            assertEquals(120, result.getDegrees(), 0.0001, "Метод add() должен корректно складывать углы.");
            assertEquals(90, angle1.getDegrees(), 0.0001, "Исходный угол не должен изменяться.");
        }

        /**
         * Тест для проверки иммутабельности класса Angle: метод subtract() должен возвращать новый экземпляр.
         */
        @Test
        void testSubtractMethodReturnsNewInstance() {
            Angle angle1 = Angle.degrees(180);
            Angle angle2 = Angle.degrees(60);
            Angle result = angle1.subtract(angle2);

            // Проверка, что возвращен новый экземпляр, а исходный объект не изменен
            assertNotSame(angle1, result, "Метод subtract() должен возвращать новый экземпляр, а не изменять исходный объект.");
            assertEquals(120, result.getDegrees(), 0.0001, "Метод subtract() должен корректно вычитать углы.");
            assertEquals(180, angle1.getDegrees(), 0.0001, "Исходный угол не должен изменяться.");
        }

        /**
         * Тест для проверки иммутабельности класса Angle: метод addRadians() должен возвращать новый экземпляр.
         */
        @Test
        void testAddRadiansMethodReturnsNewInstance() {
            Angle angle1 = Angle.degrees(90);
            double radiansToAdd = Math.PI / 2;
            Angle result = angle1.addRadians(radiansToAdd);

            // Проверка, что возвращен новый экземпляр, а исходный объект не изменен
            assertNotSame(angle1, result, "Метод addRadians() должен возвращать новый экземпляр, а не изменять исходный объект.");
            assertEquals(180, result.getDegrees(), 0.0001, "Метод addRadians() должен корректно складывать угол с радианами.");
            assertEquals(90, angle1.getDegrees(), 0.0001, "Исходный угол не должен изменяться.");
        }

        /**
         * Тест для проверки иммутабельности класса Angle: метод subtractRadians() должен возвращать новый экземпляр.
         */
        @Test
        void testSubtractRadiansMethodReturnsNewInstance() {
            Angle angle1 = Angle.degrees(180);
            double radiansToSubtract = Math.PI / 2;
            Angle result = angle1.subtractRadians(radiansToSubtract);

            // Проверка, что возвращен новый экземпляр, а исходный объект не изменен
            assertNotSame(angle1, result, "Метод subtractRadians() должен возвращать новый экземпляр, а не изменять исходный объект.");
            assertEquals(90, result.getDegrees(), 0.0001, "Метод subtractRadians() должен корректно вычитать угол с радианами.");
            assertEquals(180, angle1.getDegrees(), 0.0001, "Исходный угол не должен изменяться.");
        }
    }

    // 9. **Невалидные значения**: Тестирование на передаче углов за пределами допустимого диапазона
    @Nested
    class InvalidValueTests {

        /**
         * Тест для проверки, что создание угла с недопустимым значением в градусах выбрасывает исключение.
         */
        @ParameterizedTest(name = "Недопустимое значение градусов: {0}")
        @CsvSource({
                "361, 'Angle degrees out of bounds'",
                "-361, 'Angle degrees out of bounds'"
        })
        void testInvalidDegrees(double degrees, String description) {
            Executable executable = () -> Angle.degrees(degrees);
            assertThrows(IllegalArgumentException.class, executable, description);
        }

        /**
         * Тест для проверки, что создание угла с недопустимым значением в радианах выбрасывает исключение.
         */
        @ParameterizedTest(name = "Недопустимое значение радиан: {0}")
        @CsvSource({
                "6.4, 'Angle radians out of bounds'",
                "-6.4, 'Angle radians out of bounds'"
        })
        void testInvalidRadians(double radians, String description) {
            Executable executable = () -> Angle.radians(radians);
            assertThrows(IllegalArgumentException.class, executable, description);
        }
    }

    // 10. **Проверка исключений**: Тестирование на выбрасывание исключений для различных недопустимых случаев
    @Nested
    class ExceptionHandlingTests {

        /**
         * Тест для проверки выбрасывания IllegalArgumentException при создании угла вне допустимого диапазона.
         */
        @Test
        void testOutOfBoundsAngleCreationThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> Angle.degrees(400), "Угол вне диапазона должен вызывать IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> Angle.radians(7), "Угол вне диапазона в радианах должен вызывать IllegalArgumentException.");
        }

        /**
         * Тест для проверки выбрасывания исключений при выполнении недопустимых операций.
         */
        @Test
        void testOperationsThrowExceptionForInvalidAngles() {
            Angle angle = Angle.degrees(180);
            assertThrows(IllegalArgumentException.class, () -> angle.add(Angle.degrees(200)), "Сложение должно выбрасывать исключение, если результат выходит за пределы.");
            assertThrows(IllegalArgumentException.class, () -> angle.subtract(Angle.degrees(400)), "Вычитание должно выбрасывать исключение, если результат выходит за пределы.");
        }
    }

    // 11. **Проверка формул**: Тестирование корректности формул перевода между градусами и радианами
    @Nested
    class FormulaTests {

        /**
         * Тест для проверки формулы перевода из радианов в градусы.
         */
        @ParameterizedTest(name = "Проверка формулы радианы -> градусы: {0} радиан = {1} градусов")
        @CsvSource({
                "0, 0",
                "3.14159, 180",
                "-3.14159, -180",
                "6.28318, 360",
                "-6.28318, -360"
        })
        void testRadiansToDegreesFormula(double radians, double expectedDegrees) {
            Angle angle = Angle.radians(radians);
            assertEquals(expectedDegrees, angle.getDegrees(), 0.01, "Формула перевода радианы -> градусы некорректна.");
        }

        /**
         * Тест для проверки формулы перевода из градусов в радианы.
         */
        @ParameterizedTest(name = "Проверка формулы градусы -> радианы: {0} градусов = {1} радиан")
        @CsvSource({
                "0, 0",
                "180, 3.14159",
                "-180, -3.14159",
                "360, 6.28318",
                "-360, -6.28318"
        })
        void testDegreesToRadiansFormula(double degrees, double expectedRadians) {
            Angle angle = Angle.degrees(degrees);
            assertEquals(expectedRadians, angle.getRadians(), 0.01, "Формула перевода градусы -> радианы некорректна.");
        }
    }

    // 12. **Граничные случаи**: Тестирование углов на границах допустимого диапазона
    @Nested
    class BoundaryTests {

        /**
         * Тест для проверки углов на границах диапазона, таких как -2π и 2π.
         */
        @ParameterizedTest(name = "Граничные углы в радианах: {0}")
        @CsvSource({
                "6.28318, 'Boundary value of 2π radians'",
                "-6.28318, 'Boundary value of -2π radians'",
                "360, 'Boundary value of 360 degrees'",
                "-360, 'Boundary value of -360 degrees'"
        })
        void testBoundaryAngles(double input, String description) {
            if (Math.abs(input) > 2 * Math.PI) {
                Executable executable = () -> Angle.radians(input);
                assertThrows(IllegalArgumentException.class, executable, description);
            } else {
                Angle angle = Angle.radians(input);
                assertEquals(input, angle.getRadians(), 0.01, "Угол на границе диапазона должен корректно создаваться.");
            }
        }
    }

    // **Статические углы**: Проверка статических углов ZERO_Angle и P_Angle
    @Nested
    class StaticAngleTests {

        /**
         * Проверка, что угол ZERO_Angle инициализируется со значением 0 градусов.
         */
        @Test
        void testZeroAngle() {
            assertEquals(0.0, Angle.ZERO_Angle.getDegrees(), 0.01, "ZERO_Angle должен быть равен 0 градусов.");
            assertEquals(0.0, Angle.ZERO_Angle.getRadians(), 0.01, "ZERO_Angle должен быть равен 0 радиан.");
        }

        /**
         * Проверка, что угол P_Angle инициализируется со значением π градусов (около 3.14159 радиан).
         */
        @Test
        void testPAngle() {
            assertEquals(180.0, Angle.P_Angle.getDegrees(), 0.01, "P_Angle должен быть равен 180 градусов.");
            assertEquals(Math.PI, Angle.P_Angle.getRadians(), 0.01, "P_Angle должен быть равен π радиан.");
        }

        /**
         * Проверка равенства углов между ZERO_Angle и углом, созданным с 0 градусов.
         */
        @Test
        void testZeroAngleEquality() {
            Angle zeroAngle = Angle.degrees(0);
            assertEquals(Angle.ZERO_Angle, zeroAngle, "ZERO_Angle должен быть равен углу, созданному с 0 градусов.");
        }

        /**
         * Проверка равенства углов между P_Angle и углом, созданным с π радиан.
         */
        @Test
        void testPAngleEquality() {
            Angle pAngleFromRadians = Angle.radians(Math.PI);
            assertEquals(Angle.P_Angle, pAngleFromRadians, "P_Angle должен быть равен углу, созданному с π радиан.");
        }

        /**
         * Проверка преобразования статических углов в строку.
         */
        @Test
        void testToStringForStaticAngles() {
            assertEquals("0.00 degrees", Angle.ZERO_Angle.toString(), "Строковое представление ZERO_Angle должно быть '0.00 degrees'.");
            assertEquals("180.00 degrees", Angle.P_Angle.toString(), "Строковое представление P_Angle должно быть '180.00 degrees'.");
        }
    }

}
