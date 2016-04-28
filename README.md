# 这是毕业设计的源码。

## `spring`

- `spring-web`
- `spring-data`
- `spring-session`
- `mybatis`


## `service`

- `zookeeper`
- `kafka`


## `data source`

- `redis`
- `mysql`

![Architecture](doc/Architecture.svg)


# REST API


`hostname: localhost:300`


User Api

get `api/user`  获取所有用户

get `api/user/{userId}` 获取用户id为userId的用户

post `api/user` 添加用户

put `api/user` 更新用户

delete `api/user` 删除用户


Cooking Api

get `api/cooking` 获取所有菜谱

get `api/cooking/{cookingId}` 获取id为cookingId 的菜谱

get `api/cooking/{cookingId}/show` 获取id为cookingId 的菜谱的所有作品

get `api/cooking/{cookingId}/show/{showId}` 获取id为cookingId的菜谱的id为showId 的作品

post `api/cooking/{cookingId}/show`  向id 为cookingId 的菜谱添加作品


