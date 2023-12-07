package hotel_reserve

class ReserveHistory {

    var reserveList = mutableListOf<ReservationData>()

   fun reserveCheck () {
       if (reserveList.isEmpty()){
           println("")
           println("예약내역이 없습니다.")
           println("")
           println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
           readln()
       } else {
           reserveList.forEach {println("${reserveList.indexOf(it) + 1}. 예약자 : ${it.name}   방번호 : ${it.roomNumber}호   체크인 : ${it.checkInDate}   체크아웃 : ${it.checkOutDate}") }
           println("")
           println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
           readln()
       }
    }

    fun sortCheck () {
        var sortList =
            reserveList.sortedWith(compareBy<ReservationData> { it.checkInDate }.thenBy { it.checkOutDate }).toMutableList()
        if (reserveList.isEmpty()) {
            println("\n예약내역이 없습니다.\n")
            println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        } else {
            sortList.forEach { println("${sortList.indexOf(it) + 1}. 예약자 : ${it.name}   방번호 : ${it.roomNumber}호   체크인 : ${it.checkInDate}   체크아웃 : ${it.checkOutDate}") }
            println("")
            println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        }
    }

    fun changeReserve() {
        var beforeName = ""
        var noChangeName = true
        var reserveStatus = mutableListOf<ReservationData>()

        println("예약을 변경할 사용자 이름을 입력하세요")
        beforeName = readln()

        reserveList.forEach { if (it.name == beforeName) noChangeName = false }

        if (noChangeName) {
            System.err.println("예약된 사용자를 찾을수 없습니다.")
            println("\n엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        } else {
            reserveList.forEach { if (it.name == beforeName) reserveStatus += it }
            reserveStatus.sortWith(compareBy<ReservationData> { it.checkInDate }.thenBy { it.checkOutDate })

            mainLoop@ while (true) {
                println("${beforeName}님이 예약한 목록입니다. 변경하실 예약번호를 입력해주세요 (main을 입력하면 메인화면으로 돌아갑니다.)")

                for (list in 0..reserveStatus.lastIndex) {
                    println("${list+1}. 방번호 : ${reserveStatus[list].roomNumber}   체크인 : ${reserveStatus[list].checkInDate}   체크아웃 : ${reserveStatus[list].checkOutDate}")
                }

                var select = ""
                select = readln()

                if (select == "main") {
                    break@mainLoop
                }

                else if (select.toIntOrNull() == null) {
                    System.err.println("숫자만 입력해주세요")
                    continue
                }
                else if (select.toIntOrNull()!! - 1 in 0 .. reserveStatus.lastIndex) {

                    while (true) {

                        println("해당 예약을 어떻게 하시겠어요?")
                        println("[1] 변경   [2] 취소\n이외의 숫자를 입력하면 메뉴로 돌아갑니다.")

                        var cancel = readln().toIntOrNull()

                        if (cancel == null) {
                            System.err.println("숫자만 입력해주세요")
                            continue
                        }

                            when (cancel) {
                                1 -> {

                                }

                                2 -> {
                                    println("""
                                        [취소 유의사항]
                            체크인 3일 이전 취소 예약금 환불 불가
                            체크인 5일 이전 취소 예약금의 30% 환불
                            체크인 7일 이전 취소 예약금의 50% 환불
                            체크인 14일 이전 취소 예약금의 80% 환불
                            체크인 30일 이전 취소 예약금의 100% 환불
                                        """.trimIndent())

                                    println("취소하시겠습니까?")
                                    println("[1] 예    [2] 아니오")
                                   while (true) {
                                       var cancelSelect = readln().toIntOrNull()
                                       if (cancelSelect == null) {
                                           System.err.println("숫자만 입력해주세요.")
                                           continue
                                       }
                                       when (cancelSelect) {
                                           1 -> {
                                               reserveList.remove(reserveStatus[select.toInt() - 1])
                                               println("예약이 취소되었습니다.")
                                               println("\n엔터키를 누르면 메인 화면으로 돌아갑니다.")
                                               readln()
                                               break@mainLoop
                                           }

                                           2 -> {
                                               println("\n엔터키를 누르면 메인 화면으로 돌아갑니다.")
                                               readln()
                                               break@mainLoop
                                           }
                                       }
                                   }
                                }

                                else -> break@mainLoop

                            }
                    }
                }
            }
        }
    }
}