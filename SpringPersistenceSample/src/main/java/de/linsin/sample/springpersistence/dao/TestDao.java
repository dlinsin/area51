package de.linsin.sample.springpersistence.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * TODO document
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
public class TestDao extends JdbcDaoSupport {

    @Override
    protected void initDao() throws Exception {
        super.initDao();
        getJdbcTemplate().execute("CREATE TABLE users (name VARCHAR, age VARCHAR)");
    }

    public int getUserCount() {
        return getJdbcTemplate().queryForInt("SELECT COUNT(*) FROM users");
    }

    public void addUser(String argName, String argAge) {
        getJdbcTemplate().execute("INSERT INTO users VALUES('"+ argName + "', '" + argAge + "')");
    }

}
