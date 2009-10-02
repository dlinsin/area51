package de.linsin.sample.sitebricks.domain;

import de.linsin.sample.sitebricks.resource.Guestbook;

import java.util.Date;

/**
 * Entry in a {@link Guestbook}
 *
 * @author David Linsin - linsin@synyx.de
 */

public class Entry {
    private Integer id;
    private Date date;
    private String name;
    private String text;

    public Entry() {
    }

    public Entry(Integer argId, Date argDate, String argName, String argText) {
        id = argId;
        date = argDate;
        name = argName;
        text = argText;
    }

    public String getName() {
        return name;
    }

    public void setName(String argName) {
        name = argName;
    }

    public String getText() {
        return text;
    }

    public void setText(String argText) {
        text = argText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date argDate) {
        date = argDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer argId) {
        id = argId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (id != null ? !id.equals(entry.id) : entry.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
