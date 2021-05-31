import kotlin.math.PI;
import kotlin.math.pow

class Circle02(radius: Int) {
    val radius: Int
    val diameter: Int
        get() = radius * 2
    val circumference: Double
        get() = radius * PI * 2
    val area: Double
        get() = radius.toDouble().pow(2) * PI;

    init {
        require(radius > 0) { "Radius must be bigger than 0." }
        this.radius = radius
    }
}