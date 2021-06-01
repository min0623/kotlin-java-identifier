import java.util.GregorianCalendar
import java.util.Calendar
import kotlin.random.Random

class Person03 {
    private var firstName: String
    private var lastName: String
    private var birthday: Calendar
    private var favouriteNumber: Int

    constructor() {
        firstName = ""
        lastName = ""
        birthday = GregorianCalendar()
        favouriteNumber = Random.nextInt(42)
    }

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
        birthday = GregorianCalendar()
        favouriteNumber = Random.nextInt(42)
    }

    constructor(firstName: String, lastName: String, birthday: Calendar) {
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = birthday
        favouriteNumber = Random.nextInt(42)
    }

    fun fullName(): String {
        return "$firstName $lastName"
    }

    fun age(today: Calendar?): Int {
        if (today == null) throw NullPointerException()
        return today[Calendar.YEAR] - birthday[Calendar.YEAR]
    }

    fun favNum(): Int {
        return favouriteNumber
    }
}