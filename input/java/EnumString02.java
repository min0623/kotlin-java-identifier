package java;

public class EnumString02 {

    public enum TextStyle {
        BOLD, ITALICS, UNDERLINE, STRIKETHROUGH
    }

    public TextStyle getTextStyle(String style) {
        return TextStyle.valueOf(style.toUpperCase());
    }

}