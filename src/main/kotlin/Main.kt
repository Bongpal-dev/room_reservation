fun main() {

    val reserveData = ReserveData() // 데이터를 저장하는 클래스 인스턴스
    var menuLoop = true
    var menuSelect:Int
    var menuOne:RoomReserve

    reserveData.reserveList += (arrayListOf("1", "232", "2023-12-10", "2023-12-20"))
    reserveData.reserveList += (arrayListOf("2", "232", "2023-11-30", "2023-12-01"))
    reserveData.reserveList += (arrayListOf("3", "232", "2023-12-25", "2023-12-28"))
    reserveData.reserveList += (arrayListOf("4", "232", "2023-12-10", "2023-12-15"))


    while (menuLoop) {
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

        menuSelect = readLine()!!.toInt()

        when (menuSelect) {
            1 -> {
                menuOne = RoomReserve()
                if (menuOne.tempList.isNotEmpty()) reserveData.reserveList += menuOne.tempList
            }

            2 -> if (reserveData.reserveList.isEmpty()) {
                println("")
                println("예약내역이 없습니다.")
                println("")
                println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
                readln()
            } else {
                println("")
                reserveData.reserveCheck()
                println("")
                println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
                readln()
            }

            3 -> if (reserveData.reserveList.isEmpty()) {
                println("")
                println("예약내역이 없습니다.")
                println("")
                println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
                readln()
            } else {
                println("")
                reserveData.sortCheck()
                println("")
                println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
                readln()
            }

            4 -> menuLoop = false

        }
    }

    println("시스템을 종료합니다.")

}

