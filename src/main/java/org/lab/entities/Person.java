package org.lab.entities;

import lombok.Data;
import org.lab.serializer.annotations.JsonSerializable;
import org.lab.serializer.annotations.ToSerialize;

@Data
@JsonSerializable
public class Person {
    @ToSerialize
    private String bebra = null;
    @ToSerialize
    private String name;
    @ToSerialize
    private int age;
    @ToSerialize
    private Double dval = 5.5;
    @ToSerialize
    private short shortval = 10;
    @ToSerialize
    private boolean bool = true;

    @ToSerialize
    private Dog dog;

    @ToSerialize
    private Dog[] arr;
}
