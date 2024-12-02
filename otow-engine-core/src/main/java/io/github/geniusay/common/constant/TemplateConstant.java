package io.github.geniusay.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TemplateConstant {

    public static final List<Class<?>> ENTITY_ANNOTATION = List.of(
            AllArgsConstructor.class,
            NoArgsConstructor.class,
            Data.class
    );
}
