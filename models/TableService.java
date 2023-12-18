package models;

import presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;



public class TableService implements Model {

    private Collection<Table> tables;

    @Override
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    // Ненужный метдод
    public int changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        return -1;
    }

    // Новый метод для удаления записи
    @Override
    public void deleteReservationTable(int oldReservation) {
        for (Table table : tables) {
            Iterator<Reservation> iterator = table.getReservations().iterator();
            while (iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (reservation.getId() == oldReservation) {
                    iterator.remove();
                    System.out.println("Ваша бронь с номером " + oldReservation + " на столик " + table.getNo() + " отменена");
                    return; 
                }
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }

    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(table, reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }

}