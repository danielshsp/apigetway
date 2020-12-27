package com.vayusense.apigetway.config;

import com.vayusense.apigetway.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
@DependsOn("mongoTemplate")
@RequiredArgsConstructor
public class IndexConfig {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndex() {
        mongoTemplate.indexOps(User.class).ensureIndex(new Index().unique().on("username", Sort.Direction.ASC));
        mongoTemplate.indexOps(User.class).ensureIndex(new Index().unique().on("email", Sort.Direction.ASC));
        mongoTemplate.indexOps(User.class).ensureIndex(new Index().on("company", Sort.Direction.ASC));
        initTimeZone();

    }

    public void initTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    }
}
