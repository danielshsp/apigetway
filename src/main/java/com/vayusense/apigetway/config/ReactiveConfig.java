package com.vayusense.apigetway.config;

import com.mongodb.*;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import java.util.concurrent.TimeUnit;

@Data
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.vayusense.apigetway.repository")
@ConfigurationProperties(prefix = "primary.mongodb")
public class ReactiveConfig extends AbstractReactiveMongoConfiguration {

    //@Bean gives a name (primaryMongoTemplate) to the created MongoTemplate instance
    //Mongo DB Properties
    private String userName;
    private String password;
    private String database, uri;

    @Override
    public MongoClient reactiveMongoClient() {

//        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
        ConnectionString connString = new ConnectionString(uri);
        ConnectionPoolSettings connectionPoolSettings = ConnectionPoolSettings.builder().minSize(5).maxSize(10).maxWaitQueueSize(30).
                maxWaitTime(120, TimeUnit.SECONDS).build();
        ClusterSettings clusterSettings = ClusterSettings.builder().serverSelectionTimeout(15, TimeUnit.SECONDS).build();
        //.hosts(Arrays.asList(new ServerAddress(host,port))).build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .clusterSettings(clusterSettings)
                .connectionPoolSettings(connectionPoolSettings)
                .applyConnectionString(connString).retryWrites(true)
                //    .credentialList(Arrays.asList(credential))
                .build();

        return MongoClients.create(settings);
    }


    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public ReactiveMongoTemplate getMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClientURI(uri));
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;

    }
}
