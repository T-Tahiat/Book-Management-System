package Entitys;

public class SimpleBook extends Book {
    public SimpleBook(String title, String author, String genre) {
        super(title, author, genre);
    }

    public String showDetails() {
        return "Title: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\n";
    }
}