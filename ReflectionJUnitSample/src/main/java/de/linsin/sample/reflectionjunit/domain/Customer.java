/*
 * Copyright 2009 David Linsin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.linsin.sample.reflectionjunit.domain;

import org.joda.time.DateMidnight;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a customer
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
public class Customer {
    private final Long customerId;
    private final DateMidnight birthday;
    private String name;
    private Set<Contract> contracts;

    public Customer(Long argCustomerId, DateMidnight argBirthday) {
        customerId = argCustomerId;
        birthday = argBirthday;
        contracts = new HashSet<Contract>();
    }

    public boolean addContract(Contract argContract) {
        argContract.setOwner(this);
        return contracts.add(argContract);
    }

    public Set<Contract> getContracts() {
        return Collections.unmodifiableSet(contracts);
    }

    public String getName() {
        return name;
    }

    public void setName(String argName) {
        name = argName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public DateMidnight getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (birthday != null ? !birthday.equals(customer.birthday) : customer.birthday != null) return false;
        if (customerId != null ? !customerId.equals(customer.customerId) : customer.customerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
