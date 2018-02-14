package main;

import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Wrong arguments");
            return;
        }
        String input = new BufferedReader(new FileReader(args[1])).readLine();
        BufferedWriter output = new BufferedWriter(new FileWriter(args[2]));
        switch (args[0]) {
            case "1":
                HW1.runReduce(input, output);
                break;
            case "2":
            case "3":
                HW3.runType(input, output);
                break;
            default:
                System.out.println("Wrong arguments");
        }
        output.close();
    }
}
