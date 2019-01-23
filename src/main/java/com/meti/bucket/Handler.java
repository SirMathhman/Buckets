package com.meti.bucket;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Handler<T> extends Consumer<Object>, Predicate<T> {
}
