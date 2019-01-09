package com.meti;

import java.util.Collection;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 1/8/2019
 */
public interface Parameterized<P> {
    Collection<P> getParameters();
}
