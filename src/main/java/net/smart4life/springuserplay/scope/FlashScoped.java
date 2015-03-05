package net.smart4life.springuserplay.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

/**
 * Created by roman on 07.02.2015.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(value = "flash", proxyMode = ScopedProxyMode.TARGET_CLASS)
public @interface FlashScoped {
}
