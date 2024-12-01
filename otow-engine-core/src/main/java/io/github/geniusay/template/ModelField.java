package io.github.geniusay.template;

import java.util.Objects;

/**
 * 生成实体类的成员变量描述
 */
public class ModelField {

    // 成员变量名称
    private String name;

    // 成员变量类型
    private String clazz;

    // 属性注释
    private String description;

    public ModelField(String name, String clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public ModelField(String name, String clazz, String description) {
        this.name = name;
        this.clazz = clazz;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelField that = (ModelField) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
