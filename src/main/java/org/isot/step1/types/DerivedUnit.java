package org.isot.step1.types;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public enum DerivedUnit implements Unit {
    AREA(Map.ofEntries(
            entry(initializeDimension(BaseUnit.LENGTH), 2)
    ), "square meter", null, Map.ofEntries(
            entry(initializeFormula(BaseUnit.LENGTH), 2))),
    VOLUME(Map.ofEntries(
            entry(initializeDimension(BaseUnit.LENGTH), 3)
    ), "cubic meter", null, Map.ofEntries(
            entry(initializeFormula(BaseUnit.LENGTH), 3))),
    VELOCITY(Map.ofEntries(
            entry(initializeDimension(BaseUnit.LENGTH), 1),
            entry(initializeDimension(BaseUnit.TIME), -1)
    ), "meter per second", null, Map.ofEntries(
            entry(initializeFormula(BaseUnit.LENGTH), 1),
            entry(initializeFormula(BaseUnit.TIME), -1))),
    ACCELERATION(Map.ofEntries(
            entry(initializeDimension(BaseUnit.LENGTH), 1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "meter per second squared", null, Map.ofEntries(
            entry(initializeFormula(BaseUnit.LENGTH), 1),
            entry(initializeFormula(BaseUnit.TIME), -2))),
    DENSITY(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), -3)
    ), "kilogram per cubic meter", null, Map.ofEntries(
            entry(initializeFormula(BaseUnit.MASS), 1),
            entry(initializeFormula(BaseUnit.LENGTH), -3))),
    FORCE(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), 1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "newton", "N", Map.ofEntries(
            entry(initializeFormula(BaseUnit.MASS), 1),
            entry(initializeFormula(BaseUnit.LENGTH), 1),
            entry(initializeFormula(BaseUnit.TIME), -2))),
    PRESSURE(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), -1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "pascal", "Pa", Map.ofEntries(
            entry(initializeFormula(DerivedUnit.FORCE), 1),
            entry(initializeFormula(BaseUnit.LENGTH), -2))),
    STRESS(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), -1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "pascal", "Pa", Map.ofEntries(
            entry(initializeFormula(DerivedUnit.FORCE), 1),
            entry(initializeFormula(BaseUnit.LENGTH), -2))),
    ENERGY(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), 2),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "joule", "J", Map.ofEntries(
            entry(initializeFormula(DerivedUnit.FORCE), 1),
            entry(initializeFormula(BaseUnit.LENGTH), 1))),
    QUANTITY_OF_HEAT(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), -1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "pascal", "Pa", Map.ofEntries(
            entry(initializeFormula(DerivedUnit.FORCE), 1),
            entry(initializeFormula(BaseUnit.LENGTH), 1))),
    WORK(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), -1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "pascal", "Pa", Map.ofEntries(
            entry(initializeFormula(DerivedUnit.FORCE), 1),
            entry(initializeFormula(BaseUnit.LENGTH), 1))),
    POWER(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), 2),
            entry(initializeDimension(BaseUnit.TIME), -3)
    ), "watt", "W", Map.ofEntries(
            entry(initializeFormula(DerivedUnit.WORK), 1),
            entry(initializeFormula(BaseUnit.TIME), -1))),
    VISCOSITY_DYNAMIC(Map.ofEntries(
            entry(initializeDimension(BaseUnit.MASS), 1),
            entry(initializeDimension(BaseUnit.LENGTH), -1),
            entry(initializeDimension(BaseUnit.TIME), -1)
    ), "pascal-second", null, Map.ofEntries(
            entry(initializeFormula(DerivedUnit.PRESSURE), 1),
            entry(initializeFormula(BaseUnit.TIME), 1))),
    VISCOSITY_KINEMATIC(Map.ofEntries(
            entry(initializeDimension(BaseUnit.LENGTH), 2),
            entry(initializeDimension(BaseUnit.TIME), -1)
    ), "square meter per second", null, Map.ofEntries(
            entry(initializeFormula(BaseUnit.LENGTH), 2),
            entry(initializeFormula(BaseUnit.TIME), -1))),
    SPECIFIC_HEAT(Map.ofEntries(
            entry(initializeDimension(BaseUnit.LENGTH), 2),
            entry(initializeDimension(BaseUnit.TEMPERATURE), -1),
            entry(initializeDimension(BaseUnit.TIME), -2)
    ), "joule per kilogram - kelvin", null, Map.ofEntries(
            entry(initializeFormula(DerivedUnit.ENERGY), 1),
            entry(initializeFormula(BaseUnit.MASS), -1),
            entry(initializeFormula(BaseUnit.TEMPERATURE), -1)));

    private final LinkedHashMap<Character, Integer> dimension;
    private final String unit;
    private final String siSymbol;
    private final LinkedHashMap<String, Integer> formula;

    DerivedUnit(Map<Character, Integer> dimension, String unit, String siSymbol, Map<String, Integer> formula) {
        this.dimension = new LinkedHashMap<>(dimension);
        this.unit = unit;
        this.siSymbol = siSymbol;
        this.formula = new LinkedHashMap<>(formula);
    }

    public static Character initializeDimension(BaseUnit unit) {
        return unit.getDimensionObject()
                .keySet()
                .iterator()
                .next();
    }

    public static String initializeFormula(Unit unit) {
        return unit.getSiSymbol().orElse(null);
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
        return Optional.ofNullable(this.siSymbol);
    }

    @Override
    public Optional<String> getFormula() {
        return Optional.of(this.formula
                .entrySet()
                .stream()
                .map((entry -> entry.getKey() + UnitFormatter.getSuperScriptString(entry.getValue())))
                .reduce("",String::concat));

    }

    @Override
    public Optional<LinkedHashMap<String, Integer>> getFormulaObject() {
        return Optional.of(this.formula);
    }

}
