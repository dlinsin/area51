/*
 * Copyright 2009 David Linsin
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package de.linsin.sample.gae;

import javax.servlet.http.HttpServlet;


import de.linsin.sample.gae.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Dummy entity
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
public class HelloWorld extends HttpServlet {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("transactions-optional");
    
    @Override
    public void init() throws ServletException {
        super.init();
        EntityManager em = null;
        try {
            em = emfInstance.createEntityManager();
            em.persist(new User("David", "Linsin"));
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                em.close();
            } catch (Exception e) {/** IGNORE */}
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Hello, world!");
        EntityManager em = null;
        List<User> users;
        try {
            em = emfInstance.createEntityManager();

            Query query = em.createQuery("SELECT u FROM " + User.class.getName() + " u");
            users = query.getResultList();

            for (User user : users) {
                response.getWriter().println("my name is " + user.getName() + " " + user.getLastname());
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                em.close();
            } catch (Exception e) {/** IGNORE */}
        }

    }
}
