package Trab2;

public class Editor {

    private ListaDuplamenteLigada<String> document;
    private int cursor;

    public Editor() {
        document = new ListaDuplamenteLigada<>();
        cursor = 0;
    }

    //inserts the text at the end of the document
    public void insertEnd(String text) {
        document.add(text);
        cursor++;
    }

    //inserts the text at the of the line lineToInsert
    public void insert(String text, int lineToInsert) {
        document.add(lineToInsert, text);
        cursor++;
    }

    //inserts the text at the end of the line where the cursor is pointing to
    public void insert(String text) {
        document.add(cursor, text);
        cursor++;
    }

    //deletes all the text present in line lineToDelete
    public void delete(int lineToDelete) {
        document.remove(lineToDelete);
        cursor--;
    }

    //deletes all the text present in the line where the cursor is pointing to
    public void delete() {
        document.remove(cursor);
        cursor--;
    }

    //replaces the content from the line, lineToEdit, with text
    public void edit(String text, int lineToEdit) {
        document.set(lineToEdit, text);
    }

    //replaces the content from the line where the cursor is point to with text
    public void edit(String text) {
        document.set(cursor, text);
    }

    //
    public String print() {
        return document.toString();
    }

    //searches text on the document and returns the line and the content of that line
    public String search(String text) {
        for (int i = 1; i < document.size(); i++) {
            if (document.get(i).contains(text))
                return "Line: " + i + "\nText from line (" + i + "): " + document.get(i);
        }
        return "the text was not found in the document";
    }

    //moves the cursor to line above
    public void lineUp() {
        if (cursor > 0)
            cursor--;
    }

    //moves the cursor to the line below
    public void lineDown() {
        cursor++;
    }
}
