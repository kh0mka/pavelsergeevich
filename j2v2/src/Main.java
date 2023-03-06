import java.util.List;
public class Main {
    public static void main(String[] args) {
        House house = new House();
        house.addAppliance(new Lamp("Лампа", 25));
        house.addAppliance(new Television("Телевизор", 120));
        house.addAppliance(new Kettle("Электрический чайник", 170));
        house.addAppliance(new Refrigerator("Холодильник", 200));

        // Создаем новый поток, который включает все приборы в доме
        Thread plugInThread = new Thread(() -> {
            for (ElectricalAppliance appliance : house.getUnpluggedAppliances()) {
                house.plugInAppliance(appliance);
                appliance.use();
            }
        });

        // Создаем новый поток, который сортирует приборы по мощности в порядке возрастания
        Thread sortAscendingThread = new Thread(() -> {
            List<ElectricalAppliance> sortedByPowerAscending = house.getAppliancesSortedByPowerAscending();
            System.out.println("Сортировка мощности приборов (по возрастанию/asc): " + sortedByPowerAscending);
        });

        // Создаем новый поток, который сортирует приборы по мощности в порядке убывания
        Runnable sortDescendingRunnable = () -> {
            List<ElectricalAppliance> sortedByPowerDescending = house.getAppliancesSortedByPowerDescending();
            System.out.println("Сортировка мощности приборов (по убыванию/desc): " + sortedByPowerDescending);
        };
        Thread sortDescendingThread = new Thread(sortDescendingRunnable);

        // Запускаем первый поток, который включает все приборы в доме
        plugInThread.start();

        // Ждем пока первый поток не завершится
        try {
            plugInThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Запускаем второй поток, который сортирует приборы по мощности в порядке возрастания
        sortAscendingThread.start();

        try {
            sortAscendingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Запускаем третий поток, который сортирует приборы по мощности в порядке убывания
        Thread sortDescendingThread2 = new Thread(sortDescendingRunnable);
        sortDescendingThread2.start();

        int totalPowerConsumption = house.getTotalPower();
        System.out.println("Суммарная потребляемая мощность: " + totalPowerConsumption + " Вт");
    }
}