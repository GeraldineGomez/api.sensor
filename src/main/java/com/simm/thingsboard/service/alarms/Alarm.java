package com.simm.thingsboard.service.alarms;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alarm {

    private String id;

    private String alarmType;

    private CreateRules createRules;

    private Object clearRule;

    private boolean propagate;

    private String propagateRelationTypes;

    public Alarm() {

       this.id = UUID.randomUUID().toString();
       this.createRules = new CreateRules();
       this.propagate = false;
    }

}
