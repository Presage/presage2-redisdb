package uk.ac.imperial.presage2.db.redis;

import redis.clients.jedis.JedisPoolConfig;
import uk.ac.imperial.presage2.core.db.GenericStorageServiceTest;

public class RedisStorageTest extends GenericStorageServiceTest {

	@Override
	public void getDatabase() {
		// assume there's a redis instance available at localhost or all the
		// tests will fail.
		JedisPoolConfig config = new JedisPoolConfig();
		RedisDatabase redis = new RedisDatabase("localhost", config);
		redis.database = 10; // use db 10 in the redis instance
		try {
			redis.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		redis.pool.getResource().flushDB();

		this.db = redis;
		this.sto = redis;
	}

}
