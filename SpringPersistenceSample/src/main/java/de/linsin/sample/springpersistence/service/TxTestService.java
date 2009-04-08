package de.linsin.sample.springpersistence.service;

import de.linsin.sample.springpersistence.dao.TestDao;

/**
 * TODO document
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
public class TxTestService {
    private final TestDao dao;

    public TxTestService(TestDao argDao) {
        dao = argDao;
    }

    public void addUserToDB(String argName, String argAge) {
        dao.addUser(argName, argAge);
    }

    public void addDefaultUserToDB() {
        dao.addUser("David", "26");
    }

    public int getCount() {
        return dao.getUserCount();
    }
}
