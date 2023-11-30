class ReserveData {
    var reserveList = arrayListOf<ArrayList<String>>()


    fun saveReserve (list: ArrayList<String>) {

        var saveData = list

        saveData[0] = "예약자: ${saveData[0]}"
        saveData[1] = "방번호: ${saveData[1]}"
        saveData[2] = "체크인: ${saveData[2]}"
        saveData[3] = "체크아웃: ${saveData[3]}"

        reserveList += saveData
    }
    fun sortCheck (): List<ArrayList<String>> {

        return reserveList.sortedWith(compareBy<ArrayList<String>> {it[2]}.thenBy { it[3] })

    }

}