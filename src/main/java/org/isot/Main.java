package org.isot;

import org.isot.types.BaseUnit;
import org.isot.types.DerivedUnit;

public class Main {
    public static void main(String[] args) {

        System.out.println(DerivedUnit.ACCELERATION.getDimension());
        System.out.println(DerivedUnit.FORCE.getFormula().get());


    }
}