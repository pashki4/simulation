package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.field.Field;
import ua.com.vyshniakovpo.field.FieldConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(7, 7);
        Actions.initActions(field);
        FieldConsoleRenderer renderer = new FieldConsoleRenderer();
        renderer.render(field);
    }
//    {Coordinates@751} "2, 1" -> {Tree@752}
//    {Coordinates@753} "1, 0" -> {Rock@754}
//    {Coordinates@755} "3, 5" -> {Tree@756}
//    {Coordinates@759} "0, 3" -> {Rock@760}
//    {Coordinates@761} "1, 5" -> {Predator@762}
//    {Coordinates@765} "0, 5" -> {Tree@766}
//    {Coordinates@767} "5, 1" -> {Rock@768}
//    {Coordinates@763} "1, 6" -> {Herbivore@764}
//    {Coordinates@757} "3, 6" -> {Herbivore@758}
//    {Coordinates@769} "6, 4" -> {Herbivore@770}
}