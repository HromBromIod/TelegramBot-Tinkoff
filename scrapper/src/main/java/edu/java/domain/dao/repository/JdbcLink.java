package edu.java.domain.dao.repository;

import edu.java.domain.dao.LinkRepository;
import edu.java.domain.dto.jdbc.Link;
import edu.java.errors.DuplicateLinkException;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class JdbcLink implements LinkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void add(Link link) {
        try {
            jdbcTemplate.update("INSERT INTO link(url,updated_at,last_update) VALUES (?,?,?)",
                link.getUrl().toString(), link.getUpdatedAt(), link.getLastUpdate()
            );
        } catch (DataAccessException exception) {
            throw new DuplicateLinkException("Введена существующая ссылка!");
        }
    }

    @Transactional
    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM link WHERE link_id=?", id);
    }

    @Transactional
    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("SELECT * FROM link", new BeanPropertyRowMapper<>(Link.class));
    }

    @Override
    public List<Link> getByUri(URI uri) {
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE url=?",
            new BeanPropertyRowMapper<>(Link.class),
            uri.toString()
        );
    }
}
