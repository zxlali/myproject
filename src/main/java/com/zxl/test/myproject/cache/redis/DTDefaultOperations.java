package com.zxl.test.myproject.cache.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author Jerry
 * @version 1.0.3
 * @since 1.0.0 [00:58, 10/29/15]
 */
public class DTDefaultOperations<K, V> extends DTAbstractOperations<K, V> implements
    DTOperations<K, V> {

  public DTDefaultOperations(RedisTemplate<K, V> template) {
    super(template);
  }

  @Override
  public Long ttl(final K key) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.ttl(cacheKey);
      }
    });
  }

  @Override
  public Long incr(final K key) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.incr(cacheKey);
      }
    });
  }

  @Override
  public Long incr(final K key, final long expireSeconds) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        Long value = connection.incr(cacheKey);

        if (value <= 1) {
          connection.expire(cacheKey, expireSeconds);
        }

        return value;
      }
    });
  }

  @Override
  public Long incr(final K key, final long expired, final TimeUnit timeUnit) {
    final byte[] cacheKey = rawKey(key);
    final long timeout = TimeoutUtils.toMillis(expired, timeUnit);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        Long value = connection.incr(cacheKey);

        if (value <= 1) {
          connection.pExpire(cacheKey, timeout);
        }

        return value;
      }
    });
  }

  @Override
  public Long incrBy(final K key, final long value) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.incrBy(cacheKey, value);
      }
    });
  }

  @Override
  public Long incrBy(final K key, final long value, final long expireSeconds) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        Long result = connection.incrBy(cacheKey, value);

        if (result <= value) {
          connection.expire(cacheKey, expireSeconds);
        }

        return result;
      }
    });
  }

  @Override
  public Long incrBy(final K key, final long value, final long expired, final TimeUnit timeUnit) {
    final byte[] cacheKey = rawKey(key);
    final long timeout = TimeoutUtils.toMillis(expired, timeUnit);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        Long result = connection.incrBy(cacheKey, value);

        if (result <= value) {
          connection.pExpire(cacheKey, timeout);
        }

        return result;
      }
    });
  }

  @Override
  public Double incrBy(final K key, final double value) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Double>() {
      @Override
      public Double doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.incrBy(cacheKey, value);
      }
    });
  }

  @Override
  public Double incrBy(final K key, final double value, final long expireSeconds) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Double>() {
      @Override
      public Double doInRedis(RedisConnection connection) throws DataAccessException {
        Double result = connection.incrBy(cacheKey, value);

        if (result <= value) {
          connection.expire(cacheKey, expireSeconds);
        }

        return result;
      }
    });
  }

  @Override
  public Double incrBy(final K key, final double value, final long expired, final TimeUnit timeUnit) {
    final byte[] cacheKey = rawKey(key);
    final long timeout = TimeoutUtils.toMillis(expired, timeUnit);
    return execute(new RedisCallback<Double>() {
      @Override
      public Double doInRedis(RedisConnection connection) throws DataAccessException {
        Double result = connection.incrBy(cacheKey, value);

        if (result <= value) {
          connection.pExpire(cacheKey, timeout);
        }

        return result;
      }
    });
  }

  @Override
  public Long decr(final K key) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.decr(cacheKey);
      }
    });
  }

  @Override
  public Long decrBy(final K key, final long value) {
    final byte[] cacheKey = rawKey(key);
    return execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.decrBy(cacheKey, value);
      }
    });
  }

  @Override
  public Boolean setNX(final K key, final V value) {
    final byte[] cacheKey = rawKey(key);
    final byte[] cacheValue = rawValue(value);
    return execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.setNX(cacheKey, cacheValue);
      }
    });
  }

  @Override
  public V getSet(final K key, final V value) {
    final byte[] cacheValue = rawValue(value);
    return execute(new ValueDeserializingRedisCallback(key) {

      @Override
      protected byte[] inRedis(byte[] rawKey, RedisConnection connection) {
        return connection.getSet(rawKey, cacheValue);
      }

    });
  }
}
