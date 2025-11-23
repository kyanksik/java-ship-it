package ru.yandex.practicum;

import org.junit.jupiter.api.*;
import ru.yandex.practicum.delivery.*;

public class DeliveryCostTest {

    StandardParcel standardParcel = new StandardParcel("Стандарт", 10, "Москва", 8);
    FragileParcel fragileParcel = new FragileParcel("Хрупкая", 8, "Ереван", 5);
    PerishableParcel perishableParcel = new PerishableParcel("БыстроПортится", 5, "Минск", 8, 5);

    @Test
    public void shouldReturn20inStandartIfWeight10() {
        Assertions.assertEquals(20, standardParcel.calculateDeliveryCost());

    }

    @Test
    public void shouldReturn24inFragileIfWeight8() {
        Assertions.assertEquals(24, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn20inPerishableIfWeight5() {
        Assertions.assertEquals(20, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturnFalseIfNotExpired() {
        Assertions.assertFalse(perishableParcel.isExpired(13)); // Граничное значение (не испорчен) +
    }

    @Test
    public void shouldReturnFalseIfNotExpired2() {
        Assertions.assertFalse(perishableParcel.isExpired(12)); // обычное значение (не испорчен) +
    }

    @Test
    public void shouldReturnTrueIfExpired() {
        Assertions.assertTrue(perishableParcel.isExpired(14)); // обычное значение (испорчен) +
    }

    @Test
    public void shouldAddParcelInBox() {
        ParcelBox<Parcel> parcelBox = new ParcelBox<>(10);
        parcelBox.addParcel(perishableParcel);
        Assertions.assertEquals(5, parcelBox.getCurrentBoxWeight());

    }

    @Test
    public void shouldNotAddParcelInBoxBecauseMoreThanMaxBoxWeight() {
        ParcelBox<Parcel> parcelBox2 = new ParcelBox<>(10); // создаю новую коробку
        parcelBox2.addParcel(standardParcel); // вес коробки стал 10 добавили посылку
        System.out.println(parcelBox2.getParcelsCount()); // количество посылок в коробке после добавления
        parcelBox2.addParcel(standardParcel); // вес коробки не изменился, коробка не добавлена
        System.out.println(parcelBox2.getParcelsCount()); // так как по весу вторая не проходит кол-ва не меняется
        Assertions.assertEquals(10, parcelBox2.getCurrentBoxWeight());
    }
}