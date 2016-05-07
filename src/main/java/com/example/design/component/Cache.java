package com.example.design.component;

import java.util.Collection;

/**
 * Cache interface.
 *
 * @author lxh
 * @version 0.1
 */
public interface Cache<K extends String, V extends Collection> {
  void sort();

  V get(K key);

  void set(K key, V val);

  void remove(K key);

  void clean();

  void append(K key, V val);
}
