import java.time.LocalDate

class RoomReserve {
    val red = "\u001b[31m"
    val reset = "\u001b[0m"
    var today = LocalDate.now().toString().split("-").joinToString("").toInt()

    var name: String
    var roomNum: Int
    var checkIn: Int
    var checkOut: Int
    var reserveList = arrayListOf<String>()


    init {

        println("예약자분의 성함을 입력해주세요")
        name = readln()

        println("예약할 방번호를 입력해주세요")
        roomNum = readln().toInt()

        while (roomNum !in 100 .. 999) {
            println(red+"올바르지 않은 방번호 입니다. 방번호는 100~999 영역 이내입니다."+reset)
            println("")
            println("예약할 방번호를 입력해주세요")
            roomNum = readln().toInt()
        }

        println("체크인 날짜를 입력해주세요. (표기형식 : $today)")
        checkIn = readln().toInt()
        while (checkIn < today) {
            println(red+"체크인은 지난날짜를 선택할 수 없습니다."+reset)
            println("")
            println("체크인 날짜를 입력해주세요. (표기형식 : $today)")
            checkIn = readln().toInt()
        }

        println("체크아웃 날짜를 입력해 주세요. (표기형식 : ${checkIn + 1})")
        checkOut = readln().toInt()
        while (checkOut <= checkIn) {
            println(red+"체크인 날짜보다 이전이거나 같을 수는 없습니다."+reset)
            println("")
            println("체크아웃 날짜를 입력해 주세요. (표기형식 : ${checkIn + 1})")
            checkOut = readln().toInt()
        }

        println("""
            |========= 예약 내용을 확인해 주세요 =========
            |예약자 : $name
            |방번호 : $roomNum
            |체크인 날짜 : $checkIn
            |체크아웃 날짜 : $checkOut
            |=========================================
        """.trimMargin())

        println("예약 내용이 맞으신가요? [1] 예   [2] 아니오")
        var reserveSuccess = readln().toInt()
        if (reserveSuccess == 1) {
            reserveList = reservation(name, roomNum.toString(), checkIn.toString(), checkOut.toString() )
        } else {
            println("처음부터 다시 예약을 진행해주세요.")
            println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        }
    }

    fun reservation (name: String, roomNumber: String, checkIn: String, checkOut: String): ArrayList<String> {

        println("예약이 완료되었습니다.")
        println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
        readln()
        return arrayListOf<String>(name, roomNumber, checkIn, checkOut)
    }

}