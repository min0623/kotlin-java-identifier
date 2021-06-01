import kotlin.jvm.JvmStatic

internal open class Vehicle01 {
    protected var brand = "Ford" // Vehicle attribute
    fun honk() {                    // Vehicle method
        println("Tuut, tuut!")
    }
}

internal class Car01 : Vehicle01() {
    private val modelName = "Mustang" // Car attribute

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            // Create a myCar object
            val myCar = Car01()

            // Call the honk() method (from the Vehicle class) on the myCar object
            myCar.honk()

            // Display the value of the brand attribute (from the Vehicle class) and the value of the modelName from the Car class
            println(myCar.brand + " " + myCar.modelName)
        }
    }
}