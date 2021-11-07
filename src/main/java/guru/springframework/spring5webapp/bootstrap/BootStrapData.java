package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Publisher llcBooks = new Publisher("Books LLC", "123 Drive", "Little Rock", "Ar", "72203");
		publisherRepository.save(llcBooks);
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123345");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		Author rod = new Author("Rod", "Johnson");
		Book noEjb = new Book("J2EE Development without EJB", "1239948433");
		rod.getBooks().add(noEjb);
		noEjb.getAuthors().add(rod);
		noEjb.setPublisher(llcBooks);
		llcBooks.getBooks().add(noEjb);
		
		
		authorRepository.save(rod);
		bookRepository.save(noEjb);
		publisherRepository.save(llcBooks);
		
		
		ddd.setPublisher(llcBooks);
		llcBooks.getBooks().add(ddd);
		publisherRepository.save(llcBooks);
		
		
		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Number of Authors: " + authorRepository.count());
		System.out.println("Number of Publishers: " + publisherRepository.count());
	}

}
