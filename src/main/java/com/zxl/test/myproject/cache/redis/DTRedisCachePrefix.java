package com.zxl.test.myproject.cache.redis;

import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Jerry
 * @datetime 2015-08-22 17:09
 */
public class DTRedisCachePrefix implements RedisCachePrefix {
  private final RedisSerializer<String> serializer = new StringRedisSerializer();
  private String prefix;
  private String delimiter;

  public DTRedisCachePrefix() {}

  public DTRedisCachePrefix(String prefix, String delimiter) {
    this.prefix = prefix;
    this.delimiter = delimiter;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  @Override
  public byte[] prefix(String cacheName) {
    if (this.delimiter == null)
      this.delimiter = ":";

    return serializer.serialize(this.prefix != null ? (this.prefix.concat(delimiter) + cacheName
        .concat(delimiter)) : cacheName.concat(delimiter));
  }
}
