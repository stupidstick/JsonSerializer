package org.lab.entities;

import lombok.Data;
import org.lab.serializer.annotations.JsonSerializable;
import org.lab.serializer.annotations.ToSerialize;

@Data
@JsonSerializable
public class Dog {
    @ToSerialize
    private final String name;
    @ToSerialize
    private final double weight;
}

