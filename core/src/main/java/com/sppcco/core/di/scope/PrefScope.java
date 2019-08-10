package com.sppcco.core.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by janisharali on 27/01/17.
 * PrefScope
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PrefScope {
}
