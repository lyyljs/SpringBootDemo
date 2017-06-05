package com.lyyljs.demos.service;

public interface RedisService {
	
	public void set(String key, String value);
	
	public void set(String key, String value, long timeout);

    public String get(String key);

    public void setObject(String key, Object value);
    
    public void setObject(String key, Object value, long timeout);

    public Object getObject(String key);

    public boolean expire(String key, long timeout);

    public void delete(String key);
    
}
