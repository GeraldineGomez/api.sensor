package com.simm.thingsboard.service.alarms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Predicate {

   private String type;

   private String operation;

   private Value value;

   public Predicate() {
      this.type = "NUMERIC";
      this.value = new Value();
   }

    
}
