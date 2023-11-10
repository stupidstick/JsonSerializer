package org.lab.entities;

import lombok.Data;
import org.lab.serializer.annotations.JsonSerializable;
import org.lab.serializer.annotations.ToSerialize;

@Data
@JsonSerializable
public class Person{
    @ToSerialize
    private String str = null;
    @ToSerialize
    private String name = "AOC";
    @ToSerialize
    private int age = 12;
    @ToSerialize
    private Double dval = 5.5;
    @ToSerialize
    private short shortval = 10;
    @ToSerialize
    private boolean bool = true;

    private Integer iVal = 125;

    @ToSerialize
    private Dog dog = new Dog("Volkodav", 12.5);

    @ToSerialize
    private Dog[] arr = {new Dog("Basya", 20), new Dog("Bobik", 10), new Dog("Muhtar", 13.5)};
}

