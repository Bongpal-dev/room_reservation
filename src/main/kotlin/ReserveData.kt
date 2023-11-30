class ReserveData {
    var reserveList = mutableListOf<List<String>>()

   fun reserveCheck () {
        reserveList.forEach{ println("예약자 : ${it[0]}   방번호 : ${it[1]}   체크인 : ${it[2]}   체크아웃 : ${it[3]}") }
    }

    fun sortCheck () {
        reserveList.sortedWith(compareBy<List<String>> {it[2]}.thenBy { it[3] }).toMutableList().forEach{
            println("예약자 : ${it[0]}   방번호 : ${it[1]}   체크인 : ${it[2]}   체크아웃 : ${it[3]}")
        }
    }

}