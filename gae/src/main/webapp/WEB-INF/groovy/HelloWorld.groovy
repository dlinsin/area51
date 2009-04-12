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
import javax.persistence.*
import java.util.List
import de.linsin.sample.gae.domain.User 
import de.linsin.sample.gae.EMF 

EntityManager em = EMF.getInstance().createEntityManager()

Query query = em.createQuery("SELECT u FROM " + User.class.getName() + " u")
List<User> users = query.getResultList()

for (User user : users) {
  out.println(user.getName() + " " + user.getLastname() + "<br/>")
}
em.close();