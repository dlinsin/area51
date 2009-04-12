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
package de.linsin.sample.gae.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Dummy entity
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;


    public User() {
    }

    public User(String argName, String argLastname) {
        name = argName;
        lastname = argLastname;
    }

    public User(String argName) {
        name = argName;
        lastname = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long argId) {
        id = argId;
    }

    public String getName() {
        return name;
    }

    public void setName(String argName) {
        name = argName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String argLastname) {
        lastname = argLastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
