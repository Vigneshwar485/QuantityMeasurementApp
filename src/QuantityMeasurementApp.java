package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    // Enum for units
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Generic Length class (merged here)
    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double convertToBaseUnit() {
            return value * unit.getConversionFactor();
        }

        public boolean compare(Length other) {
            if (other == null) return false;

            return Double.compare(
                    this.convertToBaseUnit(),
                    other.convertToBaseUnit()
            ) == 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return compare(other);
        }

        @Override
        public int hashCode() {
            return Objects.hash(convertToBaseUnit());
        }
    }

    // Methods for demo

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static void demonstrateFeetEquality() {
        Length f1 = new Length(1.0, LengthUnit.FEET);
        Length f2 = new Length(1.0, LengthUnit.FEET);

        System.out.println("Feet equal: " + demonstrateLengthEquality(f1, f2));
    }

    public static void demonstrateInchesEquality() {
        Length i1 = new Length(1.0, LengthUnit.INCHES);
        Length i2 = new Length(1.0, LengthUnit.INCHES);

        System.out.println("Inches equal: " + demonstrateLengthEquality(i1, i2));
    }

    public static void demonstrateFeetInchesComparison() {
        Length f = new Length(1.0, LengthUnit.FEET);
        Length i = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Feet == Inches: " + demonstrateLengthEquality(f, i));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}