//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.01 a las 06:25:17 PM CET 
//


package com.psp.xml.libreria;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.psp.xml.libreria package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.psp.xml.libreria
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookDetailsRequest }
     * 
     */
    public BookDetailsRequest createBookDetailsRequest() {
        return new BookDetailsRequest();
    }

    /**
     * Create an instance of {@link BookDetailsResponse }
     * 
     */
    public BookDetailsResponse createBookDetailsResponse() {
        return new BookDetailsResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link CommentsDetailsRequest }
     * 
     */
    public CommentsDetailsRequest createCommentsDetailsRequest() {
        return new CommentsDetailsRequest();
    }

    /**
     * Create an instance of {@link AuthorDetailsResponse }
     * 
     */
    public AuthorDetailsResponse createAuthorDetailsResponse() {
        return new AuthorDetailsResponse();
    }

    /**
     * Create an instance of {@link Author }
     * 
     */
    public Author createAuthor() {
        return new Author();
    }

    /**
     * Create an instance of {@link CommentsDetailsResponse }
     * 
     */
    public CommentsDetailsResponse createCommentsDetailsResponse() {
        return new CommentsDetailsResponse();
    }

    /**
     * Create an instance of {@link AuthorDetailsRequest }
     * 
     */
    public AuthorDetailsRequest createAuthorDetailsRequest() {
        return new AuthorDetailsRequest();
    }

}
