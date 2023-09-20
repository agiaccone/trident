package com.nytimes.spg.smt.example.Trident;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Product {

    private long id;
    private float price;
    private String name;
    private String source;
    private String startDate = DateUtils.dateFormat.format(new Date());
    private String endDate = DateUtils.dateFormat.format(DateUtils.addDaysToDate(new Date(),14));


    public String toString() {
        String foo = "\n--------- \n";
        foo += "id: "+ this.id+",\n";
        foo += "Name: "+this.name +",\n";
        foo += "Source: "+this.source+",\n";
        foo += "Price: "+this.price+",\n";
        foo += "Start Date: "+this.startDate.toString();
        foo += "End Date: "+this.endDate.toString();
        foo += "--------- \n\n";
        return foo;
    }
}
