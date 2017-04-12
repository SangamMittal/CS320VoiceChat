package edu.ycp.cs320.groupProject.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;

//Worked with Bradley and Rathana.
//I helped figure out the fake database with Bradley for the lastNameQuery.
//Bradley, Rathana and I made progress on the InsertBook class, particularly Bradley
//Bradley and Rathana helped me with implementing insertBook in Derby Database.
//Josh: 20%
//Rathana: 35%
//Bradley: 45%


public class FakeDatabase implements IDatabase {
	
	private List<Author> authorList;
	private List<Book> bookList;
	
	public FakeDatabase() {
		authorList = new ArrayList<Author>();
		bookList = new ArrayList<Book>();
		
		// Add initial data
		readInitialData();
		
		System.out.println(authorList.size() + " authors");
		System.out.println(bookList.size() + " books");
	}

	public void readInitialData() {
		try {
			authorList.addAll(InitialData.getAuthors());
			bookList.addAll(InitialData.getBooks());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title) {
		List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				Author author = findAuthorByAuthorId(book.getAuthorId());
				result.add(new Pair<Author, Book>(author, book));
			}
		}
		return result;
	}
	
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(String lastname)
	{
		//for a specified last name, return a book
		
		List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
		
		for (Author author: authorList)
		{
			if (author.getLastname().equals(lastname))
			{
				for (Book book: bookList)
				{			
					if (book.getAuthorId() == author.getAuthorId())
					{		
				result.add(new Pair<Author, Book>(author, book));
					}
				}
				}
		}
		return result;
	}
	
	public List<Pair<Author, Book>> insertBook(String firstname, String lastname, String ISBN, int year, String title )
	{
	
		List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
		
		int authorID= -1;
		
		Book b = new Book();
		
		b.setAuthorId(authorID);
		b.setIsbn(ISBN);
		b.setPublished(year);
		b.setTitle(title);
		
		boolean authorExists= false;
		
		
		for (Author a: authorList)
		{
		if (a.getFirstname().equals(firstname) && a.getLastname().equals(lastname))
		{	
			 authorExists = true;
			 authorID = a.getAuthorId();	
		}
		}
		
		if (authorExists == false)
		{	
		Author added = new Author();
		added.setFirstname(firstname);
		added.setLastname(lastname);
		added.setAuthorId(authorList.size()+1);
		result.add( new Pair<Author, Book>(added, b) );
		authorID = authorList.size()+1;
		}
		return result;
	}

	private Author findAuthorByAuthorId(int authorId) {
		for (Author author : authorList) {
			if (author.getAuthorId() == authorId) {
				return author;
			}
		}
		return null;
	}
	
	
	/*
	private Book findBookByAuthorId(int authorId) {
		for (Book book : bookList) {
			if (book.getAuthorId() == authorId) {
				return book;
			}
		}
		return null;
	}
	*/
	
}
