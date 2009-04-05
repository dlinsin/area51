package de.linsin.sample.spring.integration;

/**
 * Simple bean doing something see http://blog.springsource.com/2009/02/13/982/
 *
 * @author David Linsin
 * @version 0.1
 * @since 0.1
 */
public class Shouter {
    public String shout(String argMessage) {
        return argMessage.toUpperCase().concat("!!!");
    }
}
