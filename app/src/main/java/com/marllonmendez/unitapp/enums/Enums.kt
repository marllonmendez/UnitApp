package com.marllonmendez.unitapp.enums

enum class ActiveField {
    FROM,
    TO
}

enum class UnitType(val value: String) {
    CENTIMETERS("Centímetros"),
    METERS("Metros"),
    KILOMETERS("Quilômetros"),
    MILES("Milhas")
}

enum class UnitLabel(val value: String) {
    CM("Cm"),
    M("M"),
    KM("Km"),
    MI("Mi")
}