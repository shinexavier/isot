package org.isot.types;

import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UnitsTest {

    public static final Map<Character,Integer> TD_MASS_DIMENSION = Map.ofEntries(
            entry('M', 1)
    );
    public static final Map<Character,Integer> TD_FORCE_DIMENSION = Map.ofEntries(
            entry('M', 1),
            entry('L', 1),
            entry('T', -2)
    );
    public static final Map<String,Integer> TD_ACCELERATION_FORMULA = Map.ofEntries(
            entry("m", 1),
            entry("s", -2)
    );
    public static final Map<String,Integer> TD_FORCE_FORMULA = Map.ofEntries(
            entry("kg", 1),
            entry("m", 1),
            entry("s", -2)
    );

    @org.junit.jupiter.api.Test
    void getDimension() {
        assertThat(BaseUnit.MASS.getDimension())
                .contains("[","M¹","]");
        assertThat(DerivedUnit.ACCELERATION.getDimension())
                .contains("[","L¹","T⁻²","]");
    }

    @org.junit.jupiter.api.Test
    void getDimensionObject() {
        assertThat(BaseUnit.MASS.getDimensionObject())
                .containsExactlyInAnyOrderEntriesOf(TD_MASS_DIMENSION);
        assertThat(DerivedUnit.FORCE.getDimensionObject())
                .containsExactlyInAnyOrderEntriesOf(TD_FORCE_DIMENSION);
    }

    @org.junit.jupiter.api.Test
    void getUnit() {
        assertThat(BaseUnit.MASS.getUnit())
                .isEqualTo("kilogram");
        assertThat(DerivedUnit.FORCE.getUnit())
                .isEqualTo("newton");
    }

    @org.junit.jupiter.api.Test
    void getSiSymbolNonNulls() {
        assertThat(BaseUnit.MASS.getSiSymbol())
                .isNotEmpty()
                .get()
                .isEqualTo("kg");
        assertThat(DerivedUnit.FORCE.getSiSymbol())
                .isNotEmpty()
                .get()
                .isEqualTo("N");
    }

    @org.junit.jupiter.api.Test
    void getSiSymbolNulls() {
        assertTrue(DerivedUnit.ACCELERATION.getSiSymbol().isEmpty());
        assertTrue(DerivedUnit.SPECIFIC_HEAT.getSiSymbol().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void getFormulaNulls() {
        assertTrue(BaseUnit.MASS.getFormula().orElseThrow().isEmpty());
        assertTrue(BaseUnit.TEMPERATURE.getFormula().orElseThrow().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void getFormulaNonNulls() {
        assertThat(DerivedUnit.ACCELERATION.getFormula().orElseThrow())
                .contains("m¹","s⁻²");
        assertThat(DerivedUnit.FORCE.getFormula().orElseThrow())
                .contains("s⁻²","m¹","kg¹");
    }

    @org.junit.jupiter.api.Test
    void getFormulaObjectNonNulls() {
        assertThat(DerivedUnit.ACCELERATION.getFormulaObject().orElseThrow())
                .containsExactlyInAnyOrderEntriesOf(TD_ACCELERATION_FORMULA);
        assertThat(DerivedUnit.FORCE.getFormulaObject().orElseThrow())
                .containsExactlyInAnyOrderEntriesOf(TD_FORCE_FORMULA);
    }

    @org.junit.jupiter.api.Test
    void getFormulaObjectNulls() {
        assertTrue(BaseUnit.MASS.getFormulaObject().orElseThrow().isEmpty());
        assertTrue(BaseUnit.TEMPERATURE.getFormulaObject().orElseThrow().isEmpty());
    }

}