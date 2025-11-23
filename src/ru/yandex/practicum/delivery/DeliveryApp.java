package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<FragileParcel> trackableParsels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standartParcelsBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fregileParcelsBox = new ParcelBox<>(80);
    private static ParcelBox<PerishableParcel> perishableParcelsBox = new ParcelBox<>(50);

    public static void main(String[] args) {


        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportAllTrackableStatus();
                    break;
                case 5:
                    checkParcelsInBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Показать статусы отслеживаемых посылок");
        System.out.println("5 - Показать содержимое коробки с посылками");
        System.out.println("0 — Завершить");
    }


    private static void addParcel() {
        System.out.println("Выберите тип посылки: 1 - Cтандартная , 2 - Хрупкая , 3 - Скоропортящаяся");
        int parcelType = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Напишите краткое описание посылки");
        String newParcelDescription = scanner.nextLine();
        System.out.println("Вес посылки:");
        int newParcelWeight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Адрес доставки:");
        String newParcelAddress = scanner.nextLine();
        System.out.println("День месяца в который была отправлена посылка:");
        int newParcelSendDay = scanner.nextInt();
        scanner.nextLine();

        if (parcelType == 1) {
            StandardParcel parcel = new StandardParcel(newParcelDescription, newParcelWeight, newParcelAddress, newParcelSendDay);
            System.out.println("Стандартная посылка создана");
            allParcels.add(parcel);
            standartParcelsBox.addParcel(parcel);
        }
        if (parcelType == 2) {
            FragileParcel parcel = new FragileParcel(newParcelDescription, newParcelWeight, newParcelAddress, newParcelSendDay);
            System.out.println("Хрупкая посылка создана");
            trackableParsels.add(parcel);
            allParcels.add(parcel);
            fregileParcelsBox.addParcel(parcel);
        }
        if (parcelType == 3) {
            System.out.println("Введите срок годности посылки в днях:");
            int newParcelTimeToLive = scanner.nextInt();
            scanner.nextLine();
            PerishableParcel parcel = new PerishableParcel(newParcelDescription, newParcelWeight, newParcelAddress, newParcelSendDay, newParcelTimeToLive);
            System.out.println("Скоропортящаяся посылка создана");
            allParcels.add(parcel);
            perishableParcelsBox.addParcel(parcel);
        }
        System.out.println("Посылка добавлена в список всех посылок");
    }


    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int total = 0;
        for (Parcel parcel : allParcels) {
            total += parcel.calculateDeliveryCost(); // Посчитать общую стоимость всех доставок и вывести на экран
        }
        System.out.println("Стоимость всех доставок = " + total);
    }

    private static void reportAllTrackableStatus() {
        for (FragileParcel parcel : trackableParsels) {
            System.out.println("Введите новую локацию посылки " + parcel.getDescription());
            String location = scanner.nextLine();
            parcel.reportStatus(location);
        }
    }

    private static void checkParcelsInBox() {
        System.out.println("Коробка с какими посылками вас интересует? 1 - Стандартными 2 - Хрупкими 3 - Скоропортящимися");
        int cmd = scanner.nextInt();
        switch (cmd) {
            case 1:
                standartParcelsBox.getAllParcels();
                break;
            case 2:
                fregileParcelsBox.getAllParcels();
                break;
            case 3:
                perishableParcelsBox.getAllParcels();
                break;
            default:
                System.out.println("Неверная команда");
        }
        scanner.nextLine();
    }
}





