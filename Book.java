package Entitys;

public abstract class Book{
	protected String title;
	protected String author;
	protected String genre;
	
	public Book(String title, String author, String genre){
		this.title = title;
		this.author = author;
		this.genre = genre;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
	
	public String getTitle(){
		return title;
	}
	public String getAuthor(){
		return author;
	}
	public String getGenre(){
		return genre;
	}

public abstract String showDetails();
}