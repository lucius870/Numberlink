package NumberLink;

import NumberLink.consoleUI.ConsoleUI;
import NumberLink.core.Field;

public class Main {

    public static void main(String[] args) {

        var field = new Field(5,5);
        var ui = new ConsoleUI(field);


        while (field.addPath()){}
        System.out.println("You are here");
        field.addPathNumber();

        ui.play();
    }
}
