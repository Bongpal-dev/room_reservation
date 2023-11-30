import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RoomReserve {
    val red = "\u001b[31m"
    val reset = "\u001b[0m"
    var today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()

    var name: String
    var roomNum: Int
    var checkIn: LocalDate = LocalDate.now()
    var checkOut: LocalDate = LocalDate.now()
    var tempList = arrayListOf<String>()


    init {

        // 이름 입력
        println("예약자분의 성함을 입력해주세요")
        name = readln()

        // 방번호 입력
        println("예약할 방번호를 입력해주세요")
        roomNum = readln().toInt()

        while (roomNum !in 100 .. 999) {
            println(red+"올바르지 않은 방번호 입니다. 방번호는 100~999 영역 이내입니다."+reset)
            println("")
            println("예약할 방번호를 입력해주세요")
            roomNum = readln().toInt()
        }

        // 체크인 날짜 입력
        var chkInComplete = true
        var chkInDate = ""

        // 체크인 날짜가 날짜형식에 맞지 않을경우 반복
        chcekInLoop@ while (chkInComplete) {
            println("체크인 날짜를 입력해주세요. (표기형식 : $today)")
            chkInDate = readln()
            try {
                checkIn = LocalDate.of(
                    chkInDate.substring(0, 4).toInt(),
                    chkInDate.substring(4, 6).toInt(),
                    chkInDate.substring(6, 8).toInt(),
                )
            } catch (e: DateTimeException) {
                println(red + "날짜를 올바르게 입력해주세요." + reset)
                println("")
                continue@chcekInLoop
            } catch (e: NumberFormatException) {
                println(red + "날짜를 올바르게 입력해주세요." + reset)
                println("")
                continue@chcekInLoop
            } catch (e: StringIndexOutOfBoundsException) {
                println(red + "날짜를 올바르게 입력해주세요." + reset)
                println("")
                continue@chcekInLoop
            }

            // 선택한 날짜가 오늘보다 지난 날짜인지 확인
            while (chkInDate.toInt() < today) {
                println(red+"체크인은 지난날짜를 선택할 수 없습니다."+reset)
                println("")
                continue@chcekInLoop
            }
            chkInComplete = false
        }

        // 체크아웃 날짜 입력
        var chkOutComplete = true
        var chkOutDate = ""

        checkOutLoop@ while (chkOutComplete) {
            println("체크아웃 날짜를 입력해 주세요. (표기형식 : ${checkIn.plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()})")
            chkOutDate = readln()
            try {
                checkOut = LocalDate.of(
                    chkOutDate.substring(0, 4).toInt(),
                    chkOutDate.substring(4, 6).toInt(),
                    chkOutDate.substring(6, 8).toInt(),
                )
            } catch (e: DateTimeException) {
                println(red + "날짜를 올바르게 입력해주세요." + reset)
                println("")
                continue@checkOutLoop
            } catch (e: NumberFormatException) {
                println(red + "날짜를 올바르게 입력해주세요." + reset)
                println("")
                continue@checkOutLoop
            } catch (e: StringIndexOutOfBoundsException) {
                println(red + "날짜를 올바르게 입력해주세요." + reset)
                println("")
                continue@checkOutLoop
            }
            while (chkOutDate.toInt() <= chkInDate.toInt()) {
                println(red+"체크인 날짜보다 이전이거나 같을 수는 없습니다."+reset)
                println("")
                continue@checkOutLoop
            }
            chkOutComplete = false
        }

        println("""
            |========= 예약 내용을 확인해 주세요 =========
            |예약자 : $name
            |방번호 : $roomNum
            |체크인 날짜 : $checkIn
            |체크아웃 날짜 : $checkOut
            |=========================================
        """.trimMargin())
        println("")
        println("예약 내용이 맞으신가요? [1] 예   [2] 아니오")
        var reserveSuccess = readln().toInt()
        if (reserveSuccess == 1) {
            tempList = reservation(name, roomNum.toString(), checkIn.toString(), checkOut.toString() )
        } else {
            println("")
            println("처음부터 다시 예약을 진행해주세요.")
            println("")
            println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        }
    }


    fun reservation (name: String, roomNumber: String, checkIn: String, checkOut: String): ArrayList<String> {
        println("")
        println("예약이 완료되었습니다.")
        println("")
        println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
        readln()
        return arrayListOf<String>(name, roomNumber, checkIn, checkOut)
    }


}