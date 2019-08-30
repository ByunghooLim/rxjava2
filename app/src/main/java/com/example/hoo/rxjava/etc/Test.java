package com.example.hoo.rxjava.etc;

import java.util.ArrayList;
import java.util.List;

class TestWildCard {

    public List<? extends Comparable> wildMethod1() {
        return new ArrayList<Long>();
    }

    public <T> List<? extends String> wildMethod12 (T t) {
        return new ArrayList<>();
    }

    public List<?> method3() {
        return new ArrayList<>();
    }
}