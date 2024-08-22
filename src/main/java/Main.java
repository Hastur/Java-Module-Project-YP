import java.util.ArrayList;
import java.util.Scanner;

import entities.Car;
import entities.Race;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n"); //Чтобы scanner.next() читал не только до пробела, а всю строку (Aston Martin, Alfa Romeo, Ford Mustang)
        ArrayList<Car> participants = new ArrayList<>();

        String anotherNameMessage = ", назовите по-другому:";
        String outOfSpeedRangeMessage = "Введите значение от 1 до 250:";

        System.out.println("Приветствуем вас на нашей гонке! Сегодня у нас трое участников, и совсем скоро мы узнаем победителя!");

        //Главный цикл добавления участников
        for (int i = 0; i < 3; i++) {
            int position = i + 1;

            //Блок ввода имени
            System.out.println(String.format("Введите автомобиль либо имя %dго участника:", position));
            String inputName;
            //При одинаковой скорости будет несколько победителей. Чтобы их различать, решил сделать имя уникальным.
            while (true) {
                inputName = scanner.next().trim();  //Пробелы по краям уникальным имя не делают, перепутать таких участников очень просто.
                if (inputName.isEmpty())
                    System.out.println("Имя не может быть пустым или состоять из пробелов" + anotherNameMessage);
                else {
                    boolean isSame = false;
                    for (Car current : participants) {
                        if (current.getName().equals(inputName)) {
                            isSame = true;
                            break;
                        }
                    }
                    if (!isSame) break;
                    else System.out.println("Такой участник уже есть" + anotherNameMessage);
                }
            }

            //Блок ввода скорости
            System.out.println("Введите скорость " + position + "го участника:");
            int inputSpeed;
            //Проверка скорости на int и на соответствие диапазону
            while (true) {
                if (scanner.hasNextInt()) {
                    inputSpeed = scanner.nextInt();
                    if (inputSpeed > 0 && inputSpeed <= 250) break;
                    else System.out.println(outOfSpeedRangeMessage);
                } else {
                    String text = scanner.next();
                    if (text.matches("[0-9]+")) //На случай ввода числа за пределами int: 97314897227297497983024
                        System.out.println(outOfSpeedRangeMessage);
                    else
                        System.out.println("Ведите целое число без любых других символов:");   //Всё остальное: 250 км/ч, 123.4 и т.п.
                }
            }

            //Справились с вводом, можно добавлять учатника
            participants.add(new Car(inputName, inputSpeed));

        }

        //Участники готовы, газуем
        System.out.println(new Race().getWinner(participants));

    }
}