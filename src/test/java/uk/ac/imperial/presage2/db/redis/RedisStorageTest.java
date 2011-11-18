/**
 * 	Copyright (C) 2011 Sam Macbeth <sm1106 [at] imperial [dot] ac [dot] uk>
 *
 * 	This file is part of Presage2.
 *
 *     Presage2 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Presage2 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser Public License
 *     along with Presage2.  If not, see <http://www.gnu.org/licenses/>.
 */
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
