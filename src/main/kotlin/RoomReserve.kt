package hotel_reserve

import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RoomReserve (val data: MutableList<ReservationData>) {

    val dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd")
    var today = LocalDate.now().format(dateFormat).toInt()

    var name: String = ""
    var roomNum: Int = 0
    var checkIn: LocalDate = LocalDate.now()
    var checkOut: LocalDate = LocalDate.now()
    var tempList:ReservationData? = null

    init {

        // 이름 입력
        while (true) {
            println("예약자분의 성함을 입력해주세요")
            name = readln()
            if (name.isEmpty()) {
                System.err.println("이름을 입력해주세요.")
                continue
            } else {
                break
            }
        }


        // 방번호 입력
        var roomNumComplete = true
        var tempRoomNum: Int?

        roomNumLoop@ while (roomNumComplete) {
            println("예약할 방번호를 입력해주세요")
            tempRoomNum = readln().toIntOrNull()

            if (tempRoomNum == null) {
                System.err.println("방번호를 올바르게 입력해주세요.\n")
                continue@roomNumLoop
            }

            if (tempRoomNum !in 100 .. 999) {
                System.err.println("올바르지 않은 방번호 입니다. 방번호는 100~999 영역 이내입니다.\n")
                continue@roomNumLoop
            }
            roomNum = tempRoomNum
            roomNumComplete = false
        }


        // 체크인 날짜 입력
        var chkInComplete = true
        var chkInDate = ""
        val roomUse = data.filter{ it.roomNumber == roomNum}.sortedBy { it.checkInDate }.toMutableList()

        println(roomUse)

        // 체크인 날짜가 날짜형식에 맞지 않을경우 반복
        checkInLoop@ while (chkInComplete) {

            println("체크인 날짜를 입력해주세요. (표기형식 : $today)")
            chkInDate = readln()

            // 입력한 날짜가 날짜형식에 맞는지, 있는 날짜인지 체크
            try {
                checkIn = LocalDate.of(
                    chkInDate.substring(0, 4).toInt(),
                    chkInDate.substring(4, 6).toInt(),
                    chkInDate.substring(6, 8).toInt(),
                )
            } catch (e: DateTimeException) {
                System.err.println("날짜를 올바르게 입력해주세요.\n")
                continue@checkInLoop
            } catch (e: NumberFormatException) {
                System.err.println("날짜를 올바르게 입력해주세요.\n")
                continue@checkInLoop
            } catch (e: StringIndexOutOfBoundsException) {
                System.err.println("날짜를 올바르게 입력해주세요.\n")
                continue@checkInLoop
            }

            // 선택한 날짜가 오늘보다 지난 날짜인지 확인
            if (chkInDate.toInt() < today) {
                System.err.println("체크인은 지난날짜를 선택할 수 없습니다.\n")
                continue@checkInLoop
            }

            // 선택한 날짜가 예약내역의 날짜와 겹치는지 확인
            for (i in roomUse) {
                if (checkIn.isAfter(i.checkInDate.minusDays(1)) && checkIn.isBefore(i.checkOutDate.plusDays(1))){
                    System.err.println("해당 날짜에 예약내역이 존재합니다.\n")
                    continue@checkInLoop
                }
            }

            chkInComplete = false
        }

        // 체크아웃 날짜 입력
        var chkOutComplete = true
        var chkOutDate: String

        checkOutLoop@ while (chkOutComplete) {

            println("체크아웃 날짜를 입력해 주세요. (표기형식 : ${checkIn.plusDays(1).format(dateFormat).toInt()})")
            chkOutDate = readln()

            try {
                checkOut = LocalDate.of(
                    chkOutDate.substring(0, 4).toInt(),
                    chkOutDate.substring(4, 6).toInt(),
                    chkOutDate.substring(6, 8).toInt(),
                )
            } catch (e: DateTimeException) {
                System.err.println("날짜를 올바르게 입력해주세요.\n")
                continue@checkOutLoop
            } catch (e: NumberFormatException) {
                System.err.println("날짜를 올바르게 입력해주세요.\n")
                continue@checkOutLoop
            } catch (e: StringIndexOutOfBoundsException) {
                System.err.println("날짜를 올바르게 입력해주세요.\n")
                continue@checkOutLoop
            }

            if (checkOut <= checkIn) {
                System.err.println("체크인 날짜보다 이전이거나 같을 수는 없습니다.\n")
                continue@checkOutLoop
            }

            for (j in roomUse) {
                if (checkOut.isAfter(j.checkInDate.minusDays(1)) && checkIn.isBefore(j.checkOutDate.plusDays(1))){
                    System.err.println("해당 날짜에 예약내역이 존재합니다.\n")
                    continue@checkOutLoop
                }
            }

            chkOutComplete = false

        }

        println("""
            |========= 예약 내용을 확인해 주세요 =========
            |      예약자 : $name
            |      방번호 : $roomNum
            |      체크인 날짜 : $checkIn
            |      체크아웃 날짜 : $checkOut
            |=========================================
            |
        """.trimMargin())
        println("예약 내용이 맞으신가요? [1] 예 ")

        var reserveCheck = readln().toIntOrNull()

        if (reserveCheck == 1) {
            tempList = ReservationData(name, roomNum, checkIn, checkOut)
            customerList.payment(CoustomerData(tempList!!.name, 0,0))
            println("\n예약이 완료되었습니다.\n")
            println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        } else {
            println("\n처음부터 다시 예약을 진행해주세요.\n")
            println("엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        }
    }

}