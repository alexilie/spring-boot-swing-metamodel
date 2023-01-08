package com.jomo.eorganism.metamodel.dto.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ComponentCountDto {

    private final String name;
    private final String type;
    private final String description;
    private final long count;

}
