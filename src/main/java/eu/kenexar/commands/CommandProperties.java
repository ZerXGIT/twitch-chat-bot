package eu.kenexar.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandProperties {
    String trigger();

    String prefix() default "!";

    boolean restricted() default false;
}
