package de.linsin.sample.spring.integration;

/**
 * Simple gateway interface see http://blog.springsource.com/2009/02/13/982/
 *
 * @author David Linsin
 * @version 0.1
 * @since 0.1
 */
public interface Gateway {

    public void send(String argMessage);

}
