import java.util.ArrayList;
import java.util.Scanner;

import entities.Car;
import entities.Race;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n"); //Чтобы scanner.next() читал не только до пробела, а всю строку (Aston Martin, Alfa Romeo, Ford Mustang)
        ArrayList<Car> participants = new ArrayList<>();

        System.out.println("Приветствуем вас на нашей гонке! Сегодня у нас трое участников, и совсем скоро мы узнаем победителя!");

        for (int i = 0; i < 3; i++) {
            int position = i + 1;

            System.out.println("Введите автомобиль либо имя " + position + "го участника:");

            String inputName;
            //При одинаковой скорости будет несколько победителей. Чтобы их различать, решил сделать имя уникальным.
            while (true) {
                inputName = scanner.next().trim();  //Пробелы по краям уникальным имя не делают, перепутать таких участников очень просто.
                if (inputName.isEmpty())
                    System.out.println("Имя не может быть пустым или состоять из пробелов.\nВведите автомобиль либо имя " + position + "го участника:");
                else {
                    boolean isSame = false;
                    for (Car current : participants) {
                        if (current.getName().equals(inputName)) {
                            isSame = true;
                            break;
                        }
                    }
                    if (!isSame) break;
                    else {
                        System.out.println("Такой участник уже есть, назовите по-другому:");
                    }
                }
            }

            System.out.println("Введите скорость " + position + "го участника:");

            int inputSpeed;
            //Проверка скорости на int и на соответствие диапазону
            while (true) {
                if (scanner.hasNextInt()) {
                    inputSpeed = scanner.nextInt();
                    if (inputSpeed > 0 && inputSpeed <= 250) break;
                    else System.out.println("Ведите число от 1 до 250:");
                } else {
                    System.out.println("Ведите целое число от 1 до 250 без любых других символов:");
                    scanner.next();
                }
            }

            participants.add(new Car(inputName, inputSpeed));

        }

        //Участники готовы, газуем
        System.out.println(new Race().getWinner(participants));

    }
}