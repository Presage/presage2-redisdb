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

import redis.clients.jedis.JedisPool;
import uk.ac.imperial.presage2.core.db.StorageService;
import uk.ac.imperial.presage2.core.db.persistent.PersistentEnvironment;

public class Environment extends JedisPoolUser implements PersistentEnvironment {

	Keys.Environment keys;

	protected Environment(long simID, StorageService db, JedisPool pool) {
		super(db, pool);
		keys = new Keys.Environment(simID);
	}

	@Override
	public Object getProperty(String key) {
		return this.getString(keys.property(key));
	}

	@Override
	public void setProperty(String key, Object value) {
		this.setString(keys.property(key), value.toString());
	}

	@Override
	public Object getProperty(String key, int timestep) {
		return this.getString(keys.property(key, timestep));
	}

	@Override
	public void setProperty(String key, int timestep, Object value) {
		this.setString(keys.property(key, timestep), value.toString());
	}

}
