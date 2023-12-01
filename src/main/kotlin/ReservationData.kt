package hotel_reserve

import java.time.LocalDate
data class ReservationData(
    val name: String,
    val roomNumber: Int,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate
)
