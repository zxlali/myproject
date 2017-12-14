package com.zxl.test.myproject.cache.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Jerry
 * @version 1.0.3
 * @since 1.0.0 [00:58, 10/29/15]
 */
public class DTRedisTemplate extends RedisTemplate<String, Object> implements
    DTRedisOperations<String, Object> {

  private final DTOperations<String, Object> dtOperations;

  public DTRedisTemplate() {
    dtOperations = new DTDefaultOperations<>(this);
  }

  @Override
  public boolean expire(final String key, final long expireSeconds) {
    return super.expire(key, expireSeconds, TimeUnit.SECONDS);
  }

  @Override
  public Object get(final String key) {
    return super.opsForValue().get(key);
  }

  @Override
  public boolean set(final String key, final Object value) {
    boolean result = false;
    try {
      super.opsForValue().set(key, value);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean set(final String key, final Object value, final long expireSeconds) {
    return this.set(key, value, expireSeconds, TimeUnit.SECONDS);
  }

  @Override
  public boolean set(final String key, final Object value, final long expired,
      final TimeUnit timeUnit) {
    boolean result = false;
    try {
      super.opsForValue().set(key, value);
      super.expire(key, expired, timeUnit);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public Long ttl(final String key) {
    return dtOperations.ttl(key);
  }

  @Override
  public Long size(final String key) {
    return super.opsForValue().size(key);
  }

  @Override
  public void removeByPattern(final String pattern) {
    Set<String> keys = super.keys(pattern);
    if (keys.size() > 0)
      super.delete(keys);
  }

  @Override
  public void putQueue(final String key, final Object value) {
    super.opsForList().leftPush(key, value);
  }

  @Override
  public Object getQueue(final String key) {
    return super.opsForList().rightPop(key);
  }

  @Override
  public Long sizeOfQueue(final String key) {
    return super.opsForList().size(key);
  }

  @Override
  public void putStack(final String key, final Object value) {
    super.opsForList().leftPush(key, value);
  }

  @Override
  public Object getStack(final String key) {
    return super.opsForList().leftPop(key);
  }

  @Override
  public Long sizeOfStack(final String key) {
    return super.opsForList().size(key);
  }

  @Override
  public Long incr(final String key) {
    return dtOperations.incr(key);
  }

  @Override
  public Long incr(final String key, final long expireSeconds) {
    return dtOperations.incr(key, expireSeconds);
  }

  @Override
  public Long incr(final String key, final long expired, final TimeUnit timeUnit) {
    return dtOperations.incr(key, expired, timeUnit);
  }

  @Override
  public Long incrBy(final String key, final long value) {
    return super.opsForValue().increment(key, value);
  }

  @Override
  public Long incrBy(final String key, final long value, final long expireSeconds) {
    return dtOperations.incrBy(key, value, expireSeconds);
  }

  @Override
  public Long incrBy(final String key, final long value, final long expired, final TimeUnit timeUnit) {
    return dtOperations.incrBy(key, value, expired, timeUnit);
  }

  @Override
  public Double incrBy(final String key, final double value) {
    return super.opsForValue().increment(key, value);
  }

  @Override
  public Double incrBy(final String key, final double value, final long expireSeconds) {
    return dtOperations.incrBy(key, value, expireSeconds);
  }

  @Override
  public Double incrBy(final String key, final double value, final long expired,
      final TimeUnit timeUnit) {
    return dtOperations.incrBy(key, value, expired, timeUnit);
  }

  @Override
  public Long decr(final String key) {
    return dtOperations.decr(key);
  }

  @Override
  public Long decrBy(final String key, final long value) {
    return dtOperations.decrBy(key, value);
  }

  @Override
  public Boolean setNX(final String key, final Object value) {
    return dtOperations.setNX(key, value);
  }

  @Override
  public Object getSet(final String key, final Object value) {
    return super.opsForValue().getAndSet(key, value);
  }
}
