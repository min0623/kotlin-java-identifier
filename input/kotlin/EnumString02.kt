import java.lang.NullPointerException

class EnumString02 {

    enum class TextStyle {
        BOLD, ITALICS, UNDERLINE, STRIKETHROUGH
    }

    fun getTextStyle(style: String?): TextStyle {
        return TextStyle.valueOf(style!!)
    }
}