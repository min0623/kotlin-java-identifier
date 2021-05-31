import java.util.*

class Person02 {
    private var firstName: String
    private var lastName: String
    private var birthday: Calendar
    private var favouriteNumber: Int

    constructor() {
        firstName = ""
        lastName = ""
        birthday = GregorianCalendar()
        val randomObj = Random()
        favouriteNumber = randomObj.nextInt(42)
    }

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
        birthday = GregorianCalendar()
        val randomObj = Random()
        favouriteNumber = randomObj.nextInt(42)
    }

    constructor(firstName: String, lastName: String, birthday: Calendar) {
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = birthday
        val randomObj = Random()
        favouriteNumber = randomObj.nextInt(42)
    }

    fun fullName(): String {
        return "$firstName $lastName"
    }

    fun age(today: Calendar): Int {
        return today[Calendar.YEAR] - birthday[Calendar.YEAR]
    }

    fun favNum(): Int {
        return favouriteNumber
    }
}