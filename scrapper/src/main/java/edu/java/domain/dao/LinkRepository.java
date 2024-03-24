package edu.java.domain.dao;


import edu.java.domain.dto.jdbc.Link;
import java.net.URI;
import java.util.List;

public interface LinkRepository {
    void add(Link object);

    void remove(Long id);

    List<Link> findAll();

    List<Link> getByUri(URI uri);
}
