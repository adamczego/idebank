package com.codecool.ideabank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class IdeaList {

    private ArrayList<Idea> ideaList;

    IdeaList(ArrayList<Idea> ideaList) {
        this.ideaList = ideaList != null ? ideaList : new ArrayList<Idea>();
    }

    public Function<String, Boolean> add = x -> this.ideaList.add(new Idea(x));
    public Function<Integer, Boolean> del = x -> this.ideaList.remove(this.ideaList.get(x - 1));
    public Runnable clear = () -> this.ideaList.clear();

    public Function<String, String> toString = type ->
            type.equals("bare") ? this.ideaList.stream().map(idea -> idea.toString.apply("bare")).collect(Collectors.joining())
                    : this.ideaList.stream().map(idea -> idea.toString.apply("")).collect(Collectors.joining());

    public Runnable display = () -> this.ideaList.forEach((idea) -> System.out.println((ideaList.indexOf(idea) + 1) + ". " + idea.toString.apply("bare")));


    public static Supplier<IdeaList> init = () -> {
        IdeaList ideaList = new IdeaList(null);
        ideaList.readListFromFile();
        ideaList.display.run();
        return ideaList;
    };



    public void writeListToFile() {
        File file = new File("ideaList.txt");

        try {
            file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true);

                try {
                    fileWriter.append(this.toString.apply(""));
                } catch (IOException e) {
                    e.printStackTrace();
                };

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }




    public void readListFromFile() {
        File file = new File("ideaList.txt");
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                this.add.apply(Idea.fromString(fileReader.nextLine()));
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
