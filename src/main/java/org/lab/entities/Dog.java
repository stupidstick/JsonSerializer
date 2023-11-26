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
public class Dog {

    @ToSerialize
    private String name;
    @ToSerialize
    private double weight;
}

