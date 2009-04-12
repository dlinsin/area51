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

import de.linsin.sample.gae.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Just take input in post method and save it
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
public class HelloWorld extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String) request.getParameter("uname");
        if (name != null && name.length() != 0) {
            EntityManager em = null;
            try {
                em = EMF.getInstance().createEntityManager();
                em.persist(new User(name));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    em.close();
                } catch (Exception e) {/** IGNORE */}
            }
        } else {
            name = "John Doe";
        }
        request.setAttribute("name", name);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("Visitors were: ");
        EntityManager em = null;
        List<User> users;
        try {
            em = EMF.getInstance().createEntityManager();

            Query query = em.createQuery("SELECT u FROM " + User.class.getName() + " u");
            users = query.getResultList();

            for (User user : users) {
                response.getWriter().println(user.getName() + " " + user.getLastname());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                em.close();
            } catch (Exception e) {/** IGNORE */}
        }

    }
}
