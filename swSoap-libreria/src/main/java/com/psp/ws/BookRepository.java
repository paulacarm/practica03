package com.psp.ws;



import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.psp.xml.libreria.Author;
import com.psp.xml.libreria.Book;
/**
 * 
 * @author Paula
 * Esta clase contiene los datos en un hashMap y proporciona un método de búsqueda para los libros.
 *
 */


@Component
public class BookRepository {
   private static final Map<String, Book> books = new HashMap<>();

   @PostConstruct
   public void initData() {
        
       Book book = new Book();
       book.setName("War and Peace");
       Author author=new Author();
       author.setName("Leo Tolstoy");
       author.setNationality("Rusia");
       book.setAuthor(author);
       book.setComments("Before I turned the last page of this massive volume, which had been neglected in my bookshelves for more than six years, War and Peace was a pending task in my mental reading universe knowing it to be one of the greatest Russian or maybe simply one of the greatest novels of all times.");
       books.put(book.getName(), book);
        
       book=new Book();
       book.setName("The Shadow of the Wind");
       author=new Author();
       author.setName("Carlos Ruiz Safón");
       author.setNationality("Spain");
       book.setAuthor(author);
       book.setComments("This is an excellent piece of literature. It contains poetic storytelling, shocking twists, thoroughly developed characters, symbolism, humor, romance, betrayal, action, sentimentality, nostalgia, and much, much more.");
       books.put(book.getName(), book);
        
       
   }

   public Book findBook(String name) {
       Assert.notNull(name, "The Book's name must not be null");
       return books.get(name);
   }
}
