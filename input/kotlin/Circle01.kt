import java.lang.IllegalArgumentException

class Circle01(radius: Int) {
    val radius: Int
    val diameter: Int
        get() = radius * 2
    val circumference: Double
        get() = radius * Math.PI * 2
    val area: Double
        get() = Math.pow(radius.toDouble(), 2.0) * Math.PI

    init {
        if (radius <= 0) throw IllegalArgumentException("Radius must be bigger than 0.")
        this.radius = radius
    }
}