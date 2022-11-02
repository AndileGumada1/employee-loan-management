package com.atlas.loan.application.persistance;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Department {
    OPS("OPS"),
    SALES("SALES"),
    DEBT_FREEZE("DEBT_FREEZE"),
    DEBT("DEBT_TECH");
    private final String department;


    public String getDepartment() {
        return department;
    }
}
