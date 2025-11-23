package ru.yandex.practicum.delivery;

public abstract class Parcel {
    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;
    static final int FRAGILE_COAST = 3;
    static final int PERISHABLE_COAST = 4;
    static final int STANDART_COAST = 2;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }


    public void packageItem() {
        defaultPackage();
    }


    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + getDeliveryAddress());
    }

    public abstract int calculateDeliveryCost();


    public void defaultPackage() {
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }
}
