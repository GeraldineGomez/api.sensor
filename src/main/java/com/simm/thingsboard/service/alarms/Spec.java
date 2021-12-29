package com.simm.thingsboard.service.alarms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spec {

    private String type;

    public Spec() {
        this.type = "SIMPLE";
    }

}
