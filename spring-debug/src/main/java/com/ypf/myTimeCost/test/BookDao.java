package com.ypf.myTimeCost;

import com.ypf.myTimeCost.anno.TimeCostal;

public class BookDao {

    @TimeCostal
    public long getPrice() {
        System.out.println("1");
        return 1L;
    }

//    @TimeCostal
    public long getNumber() {
        System.out.println("5");
        return 5L;
    }
}
