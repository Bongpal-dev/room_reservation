class ReserveData {
    var reserveList = mutableListOf<List<String>>()

   fun reserveCheck () {
        reserveList.forEach{ println("${reserveList.indexOf(it) + 1}. 예약자 : ${it[0]}   방번호 : ${it[1]}   체크인 : ${it[2]}   체크아웃 : ${it[3]}") }
    }

    fun sortCheck () {
        var sortList = reserveList.sortedWith(compareBy<List<String>> {it[2]}.thenBy { it[3] }).toMutableList()
        sortList.forEach{
            println("${sortList.indexOf(it) + 1}. 예약자 : ${it[0]}   방번호 : ${it[1]}   체크인 : ${it[2]}   체크아웃 : ${it[3]}")
        }
    }

}