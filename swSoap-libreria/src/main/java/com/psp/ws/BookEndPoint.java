package com.psp.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.psp.xml.libreria.AuthorDetailsRequest;
import com.psp.xml.libreria.AuthorDetailsResponse;
import com.psp.xml.libreria.BookDetailsRequest;
import com.psp.xml.libreria.BookDetailsResponse;
import com.psp.xml.libreria.CommentsDetailsRequest;
import com.psp.xml.libreria.CommentsDetailsResponse;
/**
 * 
 * @author Paula
 * Esta clase es la que va a tratar las peticiones.
 * Pasará la llamada al método correspondiente
 *
 */

 
@Endpoint
public class BookEndPoint 
{
    private static final String NAMESPACE_URI = "http://www.psp.com/xml/libreria";
 
    private BookRepository BookRepository;
 
    @Autowired
    public BookEndPoint(BookRepository BookRepository) {
        this.BookRepository = BookRepository;
    }
    //Metodo que devuelve todos los datos de un libro
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookDetailsRequest")
    @ResponsePayload
    public BookDetailsResponse getBook(@RequestPayload BookDetailsRequest request) {
        BookDetailsResponse response = new BookDetailsResponse();
        response.setBook(BookRepository.findBook(request.getName()));
 
        return response;
    }
    //Metodo que devuelve el autor de un libro
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthorDetailsRequest")
    @ResponsePayload
    public AuthorDetailsResponse getAuthor(@RequestPayload AuthorDetailsRequest request) {
    	AuthorDetailsResponse response = new AuthorDetailsResponse();
        response.setAuthor(BookRepository.findBook(request.getName()).getAuthor());
 
        return response;
    }
    //Metodo que devuelve el comentario de un libro
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CommentsDetailsRequest")
    @ResponsePayload
    public CommentsDetailsResponse getComments(@RequestPayload CommentsDetailsRequest request) {
    	CommentsDetailsResponse response = new CommentsDetailsResponse();
        response.setComments(BookRepository.findBook(request.getName()).getComments());
 
        return response;
    }
    
    

}
