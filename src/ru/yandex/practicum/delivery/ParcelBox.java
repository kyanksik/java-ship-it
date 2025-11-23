package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> parcelBox = new ArrayList<>();
    private final int maxBoxWeigth;
    private int currentBoxWeight = 0;
    private int parcelsCountInBox = 0;

    public ParcelBox(int maxBoxWeigth) {
        this.maxBoxWeigth = maxBoxWeigth;
    }

    public void addParcel(T parcel) {
        if (currentBoxWeight + parcel.getWeight() > maxBoxWeigth) {
            System.out.println("В коробке недостаточно места для этой посылки");
        } else {
            parcelBox.add(parcel);
            parcelsCountInBox += 1;
            currentBoxWeight += parcel.getWeight();
            System.out.println("Посылка добавлена в коробку");
            System.out.println("Коробка заполнена на " + currentBoxWeight + " из " + maxBoxWeigth);
        }
    }

    public void getAllParcels() {
        if (parcelsCountInBox == 0){
            System.out.println("Коробка пуста");
        }
        for (T parcel : parcelBox) {
            System.out.println(parcel.getDescription());
        }
    }

    public int getMaxBoxWeigth() {
        return maxBoxWeigth;
    }

    public int getCurrentBoxWeight() {
        return currentBoxWeight;
    }

    public int getParcelsCount() {
        return parcelsCountInBox;
    }
}
