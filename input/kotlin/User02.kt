data class User02(
    var email: String? = null,
    var name: String? = null,
    var password: String? = null,
    var age: Int? = null,
    var height: Double? = null
) {

    fun print() = toString()
}