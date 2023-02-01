package org.isot.types;

import java.util.LinkedHashMap;
import java.util.Optional;

public interface Unit {
    String getDimension();

    LinkedHashMap<Character, Integer> getDimensionObject();

    String getUnit();

    Optional<String> getSiSymbol();

    Optional<String> getFormula();

    Optional<LinkedHashMap<String, Integer>> getFormulaObject();
}
