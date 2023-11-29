import java.time.LocalDate

class RoomReserve {
    val red = "\u001b[31m"
    val reset = "\u001b[0m"
    var today = LocalDate.now().toString().split("-").joinToString("").toInt()

    var name: String
    var roomNum: Int
    var checkIn: Int
//    var chdekOut: Int

    init {

        println("예약자분의 성함을 입력해주세요")
        name = readln()
        println(name)

        println("예약할 방번호를 입력해주세요")
        roomNum = readln().toInt()

        while (roomNum !in 100 .. 999) {
            println(red+"올바르지 않은 방번호 입니다. 방번호는 100~999 영역 이내입니다."+reset)
            roomNum = readln().toInt()
        }

        println(roomNum)

        println("체크인 날짜를 입력해주세요. (표기형식 : $today")
        checkIn = readln().toInt()
        while (checkIn < today) {
            println(red+"체크인은 지난날짜를 선택할 수 없습니다."+reset)
            checkIn = readln().toInt()
        }


    }

    fun reservation () {

    }

}