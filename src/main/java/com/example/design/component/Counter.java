package com.example.design.component;

import java.io.Serializable;


/**
 * Counter interface.
 *
 * @author lxh
 * @version 0.1
 */
public interface Counter<K extends String, V extends Serializable> {
  boolean incr(K key);

  boolean decr(K key);

  boolean set(K key, V val);

  V get(K key);

  boolean clear(K key);
}
