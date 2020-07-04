package com.siit.spring.patterns;

/**
 * Pt obiecte care sunt expensive de obtinut
 */
public class Singleton {

    private Singleton() {

    }

    //EAGER singleton
    private static final Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    //LAZY singleton
    private static Singleton instanceLazy;

    public static Singleton getInstanceLazy() {
        if (instanceLazy == null) {
            instanceLazy = new Singleton();
        }
        return instance;
    }

}
