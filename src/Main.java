
import java.util.Scanner;

class GeometricFigure {
    private String name;
    private double sideA;
    private double sideB;

    public GeometricFigure(String name, double sideA, double sideB) {
        this.name = name;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public String getName() {
        return name;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getArea() {
        return sideA * sideB;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public void display(int index) {
        System.out.printf(
                "%-5d %-15s %-12.2f %-12.2f %-12.2f%n",
                index, name, sideA, sideB, getArea());
    }
}

class FigureManager {
    private final GeometricFigure[] figures;

    public FigureManager() {
        figures = new GeometricFigure[] {
                new GeometricFigure("Rectangle", 5, 6),
                new GeometricFigure("Square", 4, 4),
                new GeometricFigure("Figure", 3, 7),
                new GeometricFigure("Rectangle", 8, 5),
                new GeometricFigure("Square", 6, 6),
                new GeometricFigure("Figure", 2, 9),
                new GeometricFigure("Rectangle", 7, 4),
                new GeometricFigure("Square", 3, 3),
                new GeometricFigure("Figure", 5, 8),
                new GeometricFigure("Rectangle", 9, 6)
        };
    }

    public void displayAll() {
        printHeader();

        for (int i = 0; i < figures.length; i++) {
            figures[i].display(i + 1);
        }
    }

    private void printHeader() {
        System.out.printf(
                "%-5s %-15s %-12s %-12s %-12s%n",
                "№", "Назва", "Сторона A", "Сторона B", "Площа");
    }

    public void modifyFigure(Scanner scanner) {
        System.out.print("Введіть номер фігури (1-10): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > figures.length) {
            System.out.println("Помилка: неправильний номер!");
            return;
        }

        GeometricFigure figure = figures[index - 1];

        System.out.print("Введіть нову назву: ");
        String name = scanner.nextLine();

        System.out.print("Введіть нове значення сторони A: ");
        double sideA = scanner.nextDouble();

        System.out.print("Введіть нове значення сторони B: ");
        double sideB = scanner.nextDouble();

        if (sideA <= 0 || sideB <= 0) {
            System.out.println("Сторони повинні бути додатними!");
            return;
        }

        figure.setName(name);
        figure.setSideA(sideA);
        figure.setSideB(sideB);

        System.out.println("Дані фігури успішно змінено!");
    }

    public void findByArea(double minArea) {
        boolean found = false;

        System.out.println(
                "\nФігури, площа яких більша за " + minArea + ":");

        printHeader();

        for (int i = 0; i < figures.length; i++) {
            if (figures[i].getArea() > minArea) {
                figures[i].display(i + 1);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Фігур не знайдено.");
        }
    }

    public void sortByArea(boolean ascending) {
        for (int i = 0; i < figures.length - 1; i++) {
            for (int j = 0; j < figures.length - i - 1; j++) {
                boolean needSwap;

                if (ascending) {
                    needSwap = figures[j].getArea() > figures[j + 1].getArea();
                } else {
                    needSwap = figures[j].getArea() < figures[j + 1].getArea();
                }

                if (needSwap) {
                    GeometricFigure temp = figures[j];
                    figures[j] = figures[j + 1];
                    figures[j + 1] = temp;
                }
            }
        }

        System.out.println(
                ascending
                        ? "\nСортування за зростанням площі:"
                        : "\nСортування за спаданням площі:");

        displayAll();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FigureManager manager = new FigureManager();

        int choice;

        do {
            System.out.println("\n __________ МЕНЮ ПРОГРАМИ ________");
            System.out.println("1. Вивести всі фігури");
            System.out.println("2. Модифікувати фігуру");
            System.out.println("3. Знайти фігури з площею > 20");
            System.out.println("4. Сортувати за зростанням площі");
            System.out.println("5. Сортувати за спаданням площі");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.displayAll();
                    break;

                case 2:
                    manager.modifyFigure(scanner);
                    break;

                case 3:
                    manager.findByArea(20);
                    break;

                case 4:
                    manager.sortByArea(true);
                    break;

                case 5:
                    manager.sortByArea(false);
                    break;

                case 0:
                    System.out.println("Завершення програми.");
                    break;

                default:
                    System.out.println("Невірний пункт меню!");
            }
        } while (choice != 0);

        scanner.close();
    }
}
