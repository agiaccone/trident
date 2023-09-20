package com.nytimes.spg.smt.example.Trident;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Subscription {

    Long id;
    String subType;
    String name;
    private Long regiId;
    String  startDate;
    String endDate;
    List<Integer> products;


    public String toString() {
        String foo = "\n--------- \n";
        foo += "Name: "+this.name +",\n";
        foo += "subType: "+this.subType+",\n";
        foo += "regiId: "+this.regiId+",\n";
        foo += "startDate: "+this.startDate+",\n";
        foo += "endDate: "+this.endDate+"\n";
        foo += "--------- \n\n";
        return foo;
    }
}
