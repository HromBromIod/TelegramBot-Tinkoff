package edu.java.domain.dto.jdbc;

import java.net.URI;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Link {
    private Long linkId;
    private URI url;
    private OffsetDateTime updatedAt;
    private OffsetDateTime lastUpdate;

    public Link(URI url, OffsetDateTime updatedAt,OffsetDateTime lastUpdate) {
        this.url = url;
        this.updatedAt = updatedAt;
        this.lastUpdate = lastUpdate;
    }

    public Link(Long id, URI url, OffsetDateTime updatedAt, OffsetDateTime lastUpdate) {
        this.linkId = id;
        this.url = url;
        this.updatedAt = updatedAt;
        this.lastUpdate = lastUpdate;
    }
}
