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
            return "И нашим победителем становится " + participants.get(0).name + "!";
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < participants.size(); i++) {
                result.append(participants.get(i).name);
                //Если победителей несколько, их надо разделить запятыми, а двух последних союзом и
                if (i == participants.size() - 2) result.append(" и ");
                else if (i != participants.size() - 1) result.append(", ");
            }
            return "Невероятно, но " + result + " финишировали с одинаковым результатом!";
        }
    }
}