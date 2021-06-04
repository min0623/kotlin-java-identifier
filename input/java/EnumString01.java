
public class EnumString01 {

    public enum TextStyle {
        BOLD, ITALICS, UNDERLINE, STRIKETHROUGH
    }

    public TextStyle getTextStyle(String style) {
        return TextStyle.valueOf(style.toUpperCase());
    }

}