package com.codecool.ideabank;

import java.util.Scanner;


public class Ideabank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IdeaList ideaList = IdeaList.init.get();

        while(true) {
            System.out.println("Do you have a new idea? Type it in.\nPrint your prev ideas with '--list', delete one with '--delete[index]' or delete all by typing '--delete all'.\nWHen youre done exit by typing 'quit'");
            String ln = scanner.nextLine();

            if(ln.equals("quit")) {
                ideaList.writeListToFile();
                scanner.close();
                break;
            }

            if(ln.equals("--list")) {
                ideaList.display.run();
                continue;
            }

            if(ln.equals("--delete all")) {
                ideaList.clear.run();
                continue;
            }

            if(ln.startsWith("--delete")) {
                ideaList.del.apply(Integer.parseInt(ln.replaceAll("[^0-9]", "")));
                continue;
            }

            if(ln.startsWith("--")) continue;

            ideaList.add.apply(ln);
            ideaList.display.run();
        }

        ideaList.writeListToFile();



    }
}
