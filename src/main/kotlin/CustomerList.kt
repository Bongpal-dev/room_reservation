package hotel_reserve

import kotlin.random.Random

class CustomerList {
    var customerData = mutableListOf<CoustomerData>()

    fun payment (customer: CoustomerData) {

        var dataCheck = true

        customerData.forEach { if (it.name == customer.name) dataCheck = false else true }

        if (dataCheck) {
            customerData += CoustomerData(customer.name, Random.nextInt(100000, 300000),0)
        }

        for (i in customerData) {
            if (i.name == customer.name) {
                i.outMoney += Random.nextInt(15000, 30000)
            }
        }

    }

    fun moneyCheck() {
        var checkName = ""
        var noName = true

        println("조회하실 사용자 이름을 입력하세요")
        checkName = readln()

        for (c in customerData) {
            if (c.name == checkName) {
                println("1. 초기금액으로 ${c.seedMoney}원 입금되었습니다.")
                println("2. 예약금으로 ${c.outMoney}원 출금되었습니다.")
                println("3. 현재 남은 금액은 ${c.seedMoney - c.outMoney}원입니다.")
                println("\n엔터키를 누르면 메인 화면으로 돌아갑니다.")
                readln()
                noName = false
                break
            } else {
                noName = true
            }
        }

        if (noName) {
            System.err.println("예약된 사용자를 찾을수 없습니다.")
            println("\n엔터키를 누르면 메인 화면으로 돌아갑니다.")
            readln()
        }
    }

}