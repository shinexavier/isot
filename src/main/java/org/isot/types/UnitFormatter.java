package org.isot.types;

import org.apache.commons.text.StringEscapeUtils;

import java.util.Arrays;
import java.util.Objects;

public enum UnitFormatter {

    PLUS("+", "\\u207A", "\\u208A"),
    MINUS("-", "\\u207B", "\\u208B"),
    ZERO("0", "\\u2070", "\\u2080"),
    ONE("1", "\\u00B9", "\\u2081"),
    TWO("2", "\\u00B2", "\\u2082"),
    THREE("3", "\\u00B3", "\\u2083"),
    FOUR("4", "\\u2074", "\\u2084"),
    FIVE("5", "\\u2075", "\\u2085"),
    SIX("6", "\\u2076", "\\u2086"),
    SEVEN("7", "\\u2077", "\\u2087"),
    EIGHT("8", "\\u2078", "\\u2088"),
    NINE("9", "\\u2079", "\\u2089");

    private final String literal;
    private final String superScriptCode;
    private final String subScriptCode;

    UnitFormatter(String literal, String superScriptCode, String subScriptCode) {
        this.literal = literal;
        this.superScriptCode = superScriptCode;
        this.subScriptCode = subScriptCode;
    }

    public static String getSuperScriptString(Integer power) {
        String iNumber = String.valueOf(power);
        String ucString = iNumber.chars()
                .mapToObj(l -> getSuperScriptCodeByValue(String.valueOf((char) l)))
                .filter(Objects::nonNull)
                .map(UnitFormatter::getSuperScriptCode)
                .reduce("", String::concat);
        return StringEscapeUtils.unescapeJava(ucString);
    }

    public static UnitFormatter getSuperScriptCodeByValue(String value) {
        return Arrays.stream(UnitFormatter.values())
                .filter(unit -> unit.literal.equals(value))
                .findFirst()
                .orElse(null);
    }

    private String getSuperScriptCode() {
        return this.superScriptCode;
    }
}
