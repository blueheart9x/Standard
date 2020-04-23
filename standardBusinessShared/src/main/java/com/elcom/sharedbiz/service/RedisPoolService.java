package com.elcom.sharedbiz.service;

import java.time.Duration;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.elcom.model.constant.InterviewConstant;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public final class RedisPoolService {

    private static final Logger logger = Logger.getLogger(RedisPoolService.class.getName());

    private static RedisPoolService instance;

    private JedisPool pool = null;

    private static ReentrantLock lock = new ReentrantLock();

    private RedisPoolService() {

        jedisPoolConfig();
    }

    //public static synchronized RedisPoolService getInstance() { // TODO Mở lại nếu chạy gặp vấn đề
    public static RedisPoolService getInstance() {

        lock.lock();

        try {

            if (instance == null) {
                synchronized (RedisPoolService.class) {
                    instance = new RedisPoolService();
                }
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    private void jedisPoolConfig() {

        JedisPoolConfig poolConfig = buildPoolConfig();

        // Pool, host, port, isSSL
        /* Localhost */
        pool = new JedisPool(poolConfig,
                 InterviewConstant.REDIS_HOST,
                 InterviewConstant.REDIS_PORT,
                 InterviewConstant.REDIS_SSL
        );

        /* SERVER TEST */
 /*pool = new JedisPool(poolConfig
		        		, InterviewConstant.REDIS_HOST
		        		, InterviewConstant.REDIS_PORT
		        		, InterviewConstant.REDIS_TIME_OUT
		        		, InterviewConstant.REDIS_PASSWORD
		        		, InterviewConstant.REDIS_SSL
					);*/
        try {

            pool.getResource();

        } catch (JedisConnectionException e) {

            logger.error("jedisPoolConfig().ex: " + e.toString() + "\n*** SHUT DOWN APP *** ");

            System.exit(0);
        }
    }

    private JedisPoolConfig buildPoolConfig() {

        final JedisPoolConfig poolConfig = new JedisPoolConfig();

        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setBlockWhenExhausted(true);

        return poolConfig;
    }

    public Jedis getJedis() {
        try {
            return pool.getResource();
        } catch (Exception ex) {
            logger.error("getJedis().ex: " + ex.toString());
        }
        return null;
    }
}
