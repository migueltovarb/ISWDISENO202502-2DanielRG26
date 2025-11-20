package com.guevara.vehiculos;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ActiveProfiles("test")
class MongoConnectionTest {
  @Autowired
  MongoTemplate mongoTemplate;

  @Test
  void canPingMongo() {
    assertNotNull(mongoTemplate);
    Document res = mongoTemplate.getDb().runCommand(new Document("ping", 1));
    Number ok = (Number) res.get("ok");
    assertNotNull(ok);
    assertEquals(1.0d, ok.doubleValue(), 0.00001d);
  }
}