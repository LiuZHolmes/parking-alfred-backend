package com.alfred.parkingalfred.annotation;

import com.alfred.parkingalfred.enums.Role;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Authorize {
    Role[] value();
}
