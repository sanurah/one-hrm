package com.sanurah.app.constants;

public enum Gender {
    FEMALE(0),
    MALE(1),
    OTHER(3);

    private Integer value;

    Gender(Integer value) {
        this.value = value;
    }
}
