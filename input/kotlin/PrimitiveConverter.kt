object PrimitiveConverter {
    fun toInt(var1: Int): Int {
        return Integer.valueOf(var1)
    }

    fun toDouble(var1: Double): Double {
        return java.lang.Double.valueOf(var1)
    }

    fun toInt(var1: Boolean): Boolean {
        return java.lang.Boolean.valueOf(var1)
    }
}