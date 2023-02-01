package org.isot.step1;

import org.isot.step1.types.DerivedUnit;

public class Main {
    public static void main(String[] args) {

        System.out.println(DerivedUnit.ACCELERATION.getDimension());
        System.out.println(DerivedUnit.FORCE.getFormula().get());


    }
}