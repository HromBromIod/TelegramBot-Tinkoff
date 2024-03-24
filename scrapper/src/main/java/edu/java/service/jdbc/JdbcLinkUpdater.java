package edu.java.service.jdbc;

import edu.java.domain.dao.LinkRepository;
import edu.java.service.LinkUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcLinkUpdater implements LinkUpdater {

    private final LinkRepository linkRepository;
    @Override
    public int update() {
        return 0;
    }
}
