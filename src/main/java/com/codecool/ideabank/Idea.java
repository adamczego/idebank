package com.codecool.ideabank;

import java.util.function.Function;

public class Idea {
    private String idea = null;

    public Idea(String idea) {
        this.idea = idea;
    }

    public Function<String, String> toString = (type) -> type.equals("bare") ? this.idea : "Idea{" + "idea='" + this.idea + '\'' + '}' + "\n";

    public Runnable display = () -> System.out.println(this.idea);



    public static String fromString(String ideaAsString) {
//        String[] asArr = Arrays.toString(Arrays.toString(ideaAsString.split("\\{")).split("}")).split("'");
        String[] idea = ideaAsString.split("'");
        String name = idea[1];
        return name;
    }
}