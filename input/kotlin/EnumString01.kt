import java.lang.NullPointerException

class EnumString01 {

    enum class TextStyle {
        BOLD, ITALICS, UNDERLINE, STRIKETHROUGH
    }

    fun getTextStyle(style: String?): TextStyle {
        return TextStyle.valueOf(style!!.toUpperCase())
    }
}