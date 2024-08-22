package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class Race {

    public String getWinner(ArrayList<Car> participants) {
        int maxDistance = 0;

        //Определить максимальное расстояние
        for (Car current : participants) {
            if (current.speed * 24 > maxDistance) maxDistance = current.speed * 24;
        }

        //Удалить из списка всех, кто проехал меньше
        Iterator<Car> iterator = participants.iterator();
        while (iterator.hasNext()) {
            Car current = iterator.next();
            if (current.speed * 24 < maxDistance) iterator.remove();
        }

        //Форматирование вывода
        if (participants.size() == 1) {
            return "И нашим победителем становится " + participants.get(0).name + " с результатом " + formatDistance(maxDistance) + "!";
        } else {
            StringBuilder winners = new StringBuilder();
            for (int i = 0; i < participants.size(); i++) {
                winners.append(participants.get(i).name);
                //Если победителей несколько, их надо разделить запятыми, а двух последних союзом и
                if (i == participants.size() - 2) winners.append(" и ");
                else if (i != participants.size() - 1) winners.append(", ");
            }
            return "Невероятно, но проехав по " + formatDistance(maxDistance) + ", " + winners + " финишировали с одинаковым результатом!";
        }
    }

    //Для более красивого вывода расстояния и просто чтобы switch тоже использовать
    String formatDistance(int distance) {
        String correctWord = switch (distance % 10) {
            case 1 -> "километр";
            case 2, 3, 4 -> "километра";
            default -> "километров";
        };
        return distance + " " + correctWord;
    }
}