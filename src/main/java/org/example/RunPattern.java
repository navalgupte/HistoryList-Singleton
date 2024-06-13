package org.example;


public class RunPattern {
    public static void main(String[] args) {
        System.out.println("Example showing use of Singleton Pattern");
        System.out.println("---- ---- ---- ---- ----");
        System.out.println("Creating First Editor");
        System.out.println();
        SingletonGui editor1 = new SingletonGui();
        editor1.createGui();

        System.out.println("Creating Second Editor");
        System.out.println();
        SingletonGui editor2 = new SingletonGui();
        editor2.createGui();
        System.out.println("---- ---- ---- ---- ----");
    }
}