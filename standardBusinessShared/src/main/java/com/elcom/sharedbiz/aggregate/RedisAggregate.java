package com.elcom.sharedbiz.aggregate;

import java.util.Map;

import org.apache.log4j.Logger;

import com.elcom.sharedbiz.service.RedisPoolService;

import redis.clients.jedis.Jedis;

public class RedisAggregate {
	
	private static final Logger logger = Logger.getLogger(RedisAggregate.class.getName());
	
	private RedisPoolService redisService = null;
	
	public RedisAggregate() {
		
		redisService = RedisPoolService.getInstance();
	}
	
	public void hset(String key,String field,String value){
		try (Jedis jedis = redisService.getJedis()) {
			jedis.connect();
			jedis.hset(key,field ,value);
		}catch(Throwable th) {
			logger.error(".hset().ex: " + th.getMessage());
			//th.printStackTrace();
		}
	}
	
	public void set(String key,String value, int expiredInSeconds){
		try (Jedis jedis = redisService.getJedis()) {
			jedis.connect();
			jedis.set(key, value);
			jedis.expire(key, expiredInSeconds);
		}catch(Throwable th){
			logger.error(".set().ex: " + th.getMessage());
			//th.printStackTrace();
		}
	}
	
	public void set(String key, String value) {
		
		try (Jedis jedis = redisService.getJedis()) {
			
			jedis.connect();
			
			/*if( jedis.exists(key) ) 
				jedis.del(key);*/
			
			jedis.set(key, value);
			
		}catch(Throwable th){
			logger.error(".set().ex: " + th.getMessage());
			//th.printStackTrace();
		}
	}
	
	public Map<String,String> hgetAll(String key){
		try (Jedis jedis = redisService.getJedis()) {
			jedis.connect();
			return jedis.hgetAll(key);
		}catch(Throwable th){
			logger.error(".hgetAll().ex: " + th.getMessage());
			//th.printStackTrace();
			return null;
		}
	}
	
	public String get(String key){
		try (Jedis jedis = redisService.getJedis()) {
			jedis.connect();
			return jedis.get(key);
		}catch(Throwable th){
			logger.error(".get().ex: " + th.getMessage());
			//th.printStackTrace();
			return null;
		}
	}
	
	public boolean isExistsKey(String key) {
		
		try (Jedis jedis = redisService.getJedis()) {
			
			jedis.connect();
			
			return jedis.exists(key);
			
		}catch(Throwable th) {
			
			logger.error(".isExistsKey().ex: " + th.getMessage());
			//th.printStackTrace();
			
			return false;
		}
	}
	
	public void delete(String key){
		try (Jedis jedis = redisService.getJedis()) {
			jedis.connect();
			jedis.del(key);
		}catch(Throwable th){
			logger.error(".delete().ex: " + th.getMessage());
			//th.printStackTrace();
		}
	}
	
	public void hdelete(String key,String field){
		try (Jedis jedis = redisService.getJedis()) {
			jedis.connect();
			jedis.hdel(key, field);
		}catch(Throwable th){
			logger.error(".hdelete().ex: " + th.getMessage());
			//th.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		
		//Connecting to Redis on with poll config
		RedisAggregate agg = new RedisAggregate();
        
		Thread t = new Thread();
		
		for (int i =0; i<= 20; i++) {
			
			Thread thread = new Thread(){
			    public void run(){
					agg.hset("23232222222", UUID.randomUUID().toString(),UUID.randomUUID().toString());
					agg.delete("23232222222");
			    }
			};
			thread.start();
			
			 thread = new Thread(){
			    public void run(){
					agg.hset("33333333333", UUID.randomUUID().toString(),UUID.randomUUID().toString());
					agg.delete("33333333333");
			    }
			};
			thread.start();
			
			 thread = new Thread(){
			    public void run(){
					agg.hset("anhdv123456", UUID.randomUUID().toString(), UUID.randomUUID().toString());
			    }
			};
			thread.start();
			
			thread = new Thread(){
			    public void run(){
					agg.hset("44444444444444", UUID.randomUUID().toString(),UUID.randomUUID().toString());
					agg.delete("44444444444444");
			    }
			};
			thread.start();
			//System.out.println(i);
		}
		
		List<TagStateObject> myLst = new ArrayList<>();
		myLst.add(new TagStateObject("TAG-01", "ONLINE1"));
		myLst.add(new TagStateObject("TAG-02", "OFFLINE2"));
		myLst.add(new TagStateObject("TAG-03", "UPDATING3"));
		UpdateTagStateReq obj = new UpdateTagStateReq();
		obj.setTagLst(myLst);

		agg.set("myLst", JSONConverter.toJSON(obj));
		
		UpdateTagStateReq objReceive = JSONConverter.toObject(agg.get("myLst"), UpdateTagStateReq.class) ;
		if( objReceive!=null && (objReceive.getTagLst()!=null && !objReceive.getTagLst().isEmpty()) ) {
			for( TagStateObject item : objReceive.getTagLst() ) {
				System.out.println(item.getTagId() + " --- " + item.getStatus());
			}
		}else
			System.out.println("null");
		
		//agg.set("anhdv307", "đỗ việt anh 123");
		//System.out.println(agg.get("anhdv307"));
		
		Map<String, String> map2 = agg.hgetAll("anhdv12345");
		if(map2 != null && !map2.isEmpty()){
			for (Map.Entry<String, String> entry : map2.entrySet()){
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
		}else
			System.out.println("null2 key");
    }*/
}