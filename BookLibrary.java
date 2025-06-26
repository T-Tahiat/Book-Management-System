package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import Entitys.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class BookLibrary extends JFrame {
    Container c;
    JLabel titleLabel, authorLabel, genreLabel;
    JTextField titleField, authorField, genreField;
    JTextArea displayArea;
    JButton addButton,saveButton,clearButton,updateButton,deleteButton;
    Font f;
    private Book[] bookList;
    private int currentSize;

    public BookLibrary() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,50,600,600);
        setTitle("Book Collection Management System");

        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(240,248,255));
		ImageIcon img=new ImageIcon("C:\\Users\\DELL\\OneDrive\\Documents\\Book Library System\\Book Library System\\Image\\public-library-interior-16380096.jpg");
        JLabel imgLabel=new JLabel(img);
		imgLabel.setBounds(20,10,img.getIconWidth(),img.getIconHeight());
        c.add(imgLabel);

        f=new Font("Arial", Font.PLAIN, 16);

        titleLabel= new JLabel("Book Title:");
        titleLabel.setBounds (30,30,100,30);
        titleLabel.setFont(f);
        c.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(140,30,200,30);
        titleField.setFont(f);
        c.add(titleField);

        authorLabel = new JLabel("Author:");
        authorLabel.setBounds(30,80,100,30);
        authorLabel.setFont(f);
        c.add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(140,80,200,30);
        authorField.setFont(f);
        c.add(authorField);

        genreLabel = new JLabel("Genre:");
		genreLabel.setBounds(30,130,100,30);
        genreLabel.setFont(f);
        c.add(genreLabel);

        genreField = new JTextField();
        genreField.setBounds(140, 130, 200,30);
        genreField.setFont(f);
        c.add(genreField);

       addButton=new JButton("Add Book");
	   addButton.setBounds(370,30,150,30);
        c.add(addButton);

        clearButton= new JButton("Clear");
        clearButton.setBounds(370,80,150,30);
        c.add(clearButton);

        saveButton=new JButton("Save");
        saveButton.setBounds(370,130,150,30);
        c.add(saveButton);

        updateButton=new JButton("Update");
        updateButton.setBounds(370,230,150,30);
        c.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(370,280,150,30);
        c.add(deleteButton);

        displayArea = new JTextArea();
        displayArea.setBounds(30,330,500,200);
        displayArea.setFont(f);
        c.add(displayArea);

    bookList = new Book[100];
currentSize = 0;

addButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e) {
        if (currentSize >=100) {
            JOptionPane.showMessageDialog(null,"Book list is full!");
            return;
        }
        String title =titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();

        Book b = new SimpleBook(title,author, genre);
        bookList[currentSize]= b;
        currentSize++;

        displayArea.append(b.showDetails() + "\n");

        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
    }
});

updateButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < currentSize; i++){
            Book b = bookList[i];
            if (titleField.getText().equals(b.getTitle())){
                String newAuthor = authorField.getText();
                String newGenre=genreField.getText();

                if (newAuthor.equals("")== false) {
                    b.setAuthor(newAuthor);
                }
                if (newGenre.equals("")== false) {
                    b.setGenre(newGenre);
                }
                break;
            }
        }
    }
});


deleteButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < currentSize; i++){
            if (titleField.getText().equals(bookList[i].getTitle())) {
                for (int j = i; j < currentSize - 1; j++) {
                    bookList[j]= bookList[j + 1];
                }
                bookList[currentSize - 1]=null;
                currentSize--;
                break;
            }
        }
    }
});

clearButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        displayArea.setText("");
        for (int i = 0; i < currentSize; i++) {
            bookList[i] = null;
        }
        currentSize = 0;
    }
});



saveButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
		try {
            File file = new File("./Data/userdata.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs(); 
                file.createNewFile(); 
            }

    
            try (
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
            ) {
				for (int i = 0; i < currentSize; i++){
			    Book b = bookList[i];
                pw.println("Title: " + b.getTitle());
                pw.println("Author: " + b.getAuthor());
				pw.println("Genre: " + b.getGenre());
                pw.println("---------------------------------------");}
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Saving data");
        }
    }
});
	}

	}
