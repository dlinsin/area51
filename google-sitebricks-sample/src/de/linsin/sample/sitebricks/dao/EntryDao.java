package de.linsin.sample.sitebricks.dao;

import de.linsin.sample.sitebricks.domain.Entry;

import java.util.List;

/**
 * Interface used to talk to persistent storage
 *
 * @author David Linsin - linsin@synyx.de
 */
public interface EntryDao {

    Integer save(Entry entry);

    Entry read(Integer id);

    List<Entry> readAll();

    void delete(Entry entry);
}
