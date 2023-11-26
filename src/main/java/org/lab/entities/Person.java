package org.lab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lab.serializer.annotations.JsonSerializable;
import org.lab.serializer.annotations.ToSerialize;

@Data
@JsonSerializable
@AllArgsConstructor
@NoArgsConstructor
public class Person{
    @ToSerialize
    private int id;

    @ToSerialize
    private String name;

    @ToSerialize
    private byte age;

    @ToSerialize
    private char gender;

    @ToSerialize
    private double weight;

    @ToSerialize
    private Dog dog;

    @ToSerialize
    private Dog[] dogs;

    @ToSerialize
    private int[] children;
}

