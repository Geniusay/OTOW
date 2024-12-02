package io.github.geniusay.template.sql.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标记字段为长文本类型
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TextType {
}