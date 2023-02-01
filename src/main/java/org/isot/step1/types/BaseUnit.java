package org.isot.step1.types;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public enum BaseUnit implements Unit {
    LENGTH(Map.ofEntries(entry('L', 1)), "meter", "m", null),
    MASS(Map.ofEntries(entry('M', 1)), "kilogram", "kg", null),
    TEMPERATURE(Map.ofEntries(entry('Q', 1)), "kelvin", "K", null),
    TIME(Map.ofEntries(entry('T', 1)), "second", "s", null);

    private final LinkedHashMap<Character, Integer> dimension;
    private final String unit;
    private final String siSymbol;
    private final LinkedHashMap<String, Integer> formula;

    BaseUnit(Map<Character, Integer> dimension, String unit, String siSymbol, Map<String, Integer> formula) {
        this.dimension = new LinkedHashMap<>(dimension);
        this.unit = unit;
        this.siSymbol = siSymbol;
        this.formula = formula == null ? new LinkedHashMap<>() : new LinkedHashMap<>(formula);
    }

    @Override
    public String getDimension() {
        return "[" +
                this.dimension
                        .entrySet()
                        .stream()
                        .map((entry -> entry.getKey() + UnitFormatter.getSuperScriptString(entry.getValue())))
                        .reduce("",String::concat) +
                "]";
    }

    @Override
    public LinkedHashMap<Character, Integer> getDimensionObject() {
        return this.dimension;
    }

    @Override
    public String getUnit() {
        return this.unit;
    }

    @Override
    public Optional<String> getSiSymbol() {
        return Optional.of(this.siSymbol);
    }

    @Override
    public Optional<String> getFormula() {
        return Optional.of(this.formula
                .entrySet()
                .stream()
                .map((entry -> entry.getKey() + UnitFormatter.getSuperScriptString(entry.getValue())))
                .reduce("", String::concat));

    }

    @Override
    public Optional<LinkedHashMap<String, Integer>> getFormulaObject() {
        return Optional.of(this.formula);
    }

}
