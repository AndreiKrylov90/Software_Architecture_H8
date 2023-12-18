package views;

import models.Table;
import presenters.View;
import presenters.ViewObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BookingView implements View {

    private Collection<ViewObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(ViewObserver observer) {
        observers.add(observer);
    }

    public void showTables(Collection<Table> tables){
        for (Table table: tables) {
            System.out.println(table);
        }
    }

    @Override
    public void showReservationTableResult(int reservationNo) {
        if (reservationNo > 0)
            System.out.printf("Столик успешно забронирован. Номер вашей брони: #%d\n", reservationNo);
        else
            System.out.println("Не удалось забронировать столик. Повторите попытку позже.");

    }
    // Новый метод для изменения бронирования - отменяет старое бронирование и создает новое
    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        deleteReservationTable(oldReservation);

        reservationTable(reservationDate, tableNo, name);
    }



    public void reservationTable(Date orderDate, int tableNo, String name){
        for (ViewObserver observer : observers){
            observer.onReservationTable(orderDate, tableNo, name);
        }
    }

    // Новый метод, который просто удаляет старую запись в случае простой отмены
    public void deleteReservationTable(int oldReservation){
        for (ViewObserver observer : observers){
            observer.deleteReservationTable(oldReservation);
        }
    }

}
