# 淘淘商城
1. 20180912
## 淘淘商城后台管理

### 商品类目的显示
## 1. 后台管理
### 1.1 功能描述
后台选择类目，加载类目树
### 1.2 请求说明
> 请求方式：GET<br>
请求URL ：[http://localhost:8081/rest/item/cat?id=1](#)

### 1.3 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
id       |long        |类目父节点
### 1.4 返回结果
```
[
    {
        "created": 1413369115000,
        "updated": 1413369115000,
        "id": 2,
        "parentId": 1,
        "name": "电子书刊",
        "status": "1",
        "sortOrder": 1,
        "isParent": true,
        "state": "closed",
        "text": "电子书刊"
    }
]
```
### 1.5 返回参数
字段       	|字段类型   |字段说明
------------|-----------|-----------
created		|string		|创建时间
updated     |string     |更新时间
id       	|long       |当前节点的ID
parentId    |long       |父节点的ID
name       	|string     |当前节点的名称
status      |string     |状态 1(正常),2(删除)
sortOrder   |string     |排序
isParent    |string     |是否是父节点
state       |string     |当前节点的状态
text       	|string     |节点显示的字段