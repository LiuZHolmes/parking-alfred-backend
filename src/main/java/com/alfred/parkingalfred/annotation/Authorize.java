package com.alfred.parkingalfred.annotation;

import com.alfred.parkingalfred.enums.RoleEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Authorize {
    RoleEnum[] value();
}
