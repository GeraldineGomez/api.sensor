package com.simm.thingsboard.service.alarms;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredicateAlarm {

   private String type;

   private String operation;

   private List<Predicate> predicates;

   public PredicateAlarm() {
      this.predicates = new ArrayList<Predicate>();
   }
    
}
