package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {


    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * Parcel.FRAGILE_COAST;
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + this.getDescription() + " обёрнута в защитную плёнку");
        defaultPackage();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }
}
