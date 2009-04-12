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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dlinsin
 * Date: Apr 12, 2009
 * Time: 3:29:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Reset extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = null;
        List<User> users;
        try {
            em = EMF.getInstance().createEntityManager();

            Query query = em.createQuery("SELECT u FROM " + User.class.getName() + " u");
            users = query.getResultList();

            for (User user : users) {
                em.remove(user);
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
