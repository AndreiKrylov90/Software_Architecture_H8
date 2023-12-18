package presenters;

import java.util.Date;

public interface ViewObserver {

    void onReservationTable(Date orderDate, int tableNo, String name);
 
    // Новый метод для удаления записи
    void deleteReservationTable(int oldReservation);
}
