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
