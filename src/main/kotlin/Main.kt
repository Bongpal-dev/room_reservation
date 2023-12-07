package hotel_reserve

val reservationHistory = ReserveHistory()
val customerList = CustomerList()

fun main() {

    var menuSelector: Int
    var reserve:RoomReserve

    while (true) {

        println(
                    """
             ========== 호텔예약 프로그램 입니다 ==========
                              
                    (1) 방예약
                    (2) 예약목록 출력
                    (3) 예약목록 (정렬) 출력
                    (4) 시스템 종료
                    (5) 금액 입금-출금 내역 출력
                    (6) 예약 변경/취소
              
              =========================================
            """.trimMargin()
        )

        // 메뉴 선택시 숫자 이외의 값이 입력되면 NumberFormatException이 발생 -> 해당 예외를 catch하여 err 메시지를 출력하고 다시 메뉴를 선택하도록 continue
        try {
            menuSelector = readln().toInt()
        } catch (e: NumberFormatException) {
            System.err.println("\n\n숫자만 입력해 주세요\n")
            continue
        }

        // menu 선택시 작동할 선택지, 메뉴 이외의 숫자를 선택하면 초기로 되돌림
        when (menuSelector) {
            1 -> {
                reserve = RoomReserve(reservationHistory.reserveList)
                if (reserve.tempList != null) {
                    reservationHistory.reserveList += reserve.tempList!!
                }
            }

            2 -> reservationHistory.reserveCheck()

            3 -> reservationHistory.sortCheck()

            4 -> break

            5 -> customerList.moneyCheck()

            6 -> reservationHistory.changeReserve()

            else -> System.err.println("메뉴에 해당하는 숫자를 입력해주세요\n")

        }
    }

    println("시스템을 종료합니다.")

}

