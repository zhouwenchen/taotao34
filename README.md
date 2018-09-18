# 淘淘商城
1. 20180912
## 淘淘商城后台管理

## 1. 后台管理
### 商品类目的显示
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

### 2 新增商品
### 2.1 功能描述
新增某一个类目下的商品数据
### 2.2 请求说明
> 请求方式：POST<br>
请求URL ：[http://localhost:8081/rest/item/](#)

### 2.3 请求参数(序列化成字符串)
```
{
	cid: 235
	title: 得力(deli)学生文具礼盒套装铅笔卷笔刀橡皮擦握笔器17件套 公主粉68896
	sellPoint: 开学刚上课，文具巧助攻，学习如鱼得水 。更有优惠券抵运费，快进来看看吧~~~
	priceView: 35.00
	price: 3500
	num: 33
	barcode: abcdefg
	image: 
	desc: 请了解一下“<span>优秀</span>”
	itemParams: []
}
```
### 2.4 返回结果
```
｛"code":201｝
```

+ 由于商品描述信息比较大，故为了查询的性能，将商品详情和商品描述，分开存放到不同的表
故此，需要考虑到事务的问题。

### 3.查询商品列表数据
### 3.1 请求说明
> 请求方式：GET<br>
请求URL ：[http://manage.taotao.com/rest/item?page=1&rows=30](#)

### 3.2 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
page       |long        |第几页
rows       |long        |每页条数
### 3.3返回结果
```
{
    "total": 3108,
    "rows": [
        {
            "created": 1536829300000,
            "updated": 1536829300000,
            "id": 143771131488381,
            "title": "得力(deli)学生文具礼盒套装铅笔卷笔刀橡皮擦握笔器17件套 公主粉68896",
            "sellPoint": "开学刚上课，文具巧助攻，学习如鱼得水 。更有优惠券抵运费，快进来看看吧~~~",
            "price": 3500,
            "num": 33,
            "barcode": "abcdefg",
            "image": "",
            "cid": 235,
            "status": 1
        }
	]
}
```

### 4.加载商品的描述信息
> 请求方式：GET<br>
请求URL ：[http://manage.taotao.com/rest/item/desc/itemId](#)

### 4.2 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
itemId       |long        |商品ID
### 4.3返回结果
```
{
    "created":1536829384000,
    "updated":1536829384000,
    "itemId":143771131488381,
    "itemDesc":"请了解一下“<span>优秀</span>”"
}
```

### 5.更新商品的信息
> 请求方式：PUT<br>
请求URL ：[http://manage.taotao.com/rest/item/](#)

### 5.2 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
desc       |string        |商品描述信息
item       |Item        |商品的信息
### 5.3返回结果
```

    "total":8,
    "rows":[
        {
            "created":1505823276000,
            "updated":1505823276000,
            "id":27,
            "itemCatId":172,
            "paramData":"[{"group":"主体","params":["品牌","系列","型号","类型"]},{"group":"核心","params":["核心数量"]},{"group":"规格","params":["接口类型","主频","制程工艺","功率","64位支持"]}]"
        },
        {
            "created":1428027682000,
            "updated":1428027682000,
            "id":1,
            "itemCatId":3,
            "paramData":"[{"group":"组名1","params":["组员1","组员2"]},{"group":"组名2","params":["组员1","组员2"]},{"group":"组名3","params":["组员1","组员2","组员3","组员4"]}]"
        }
    ]
}
```

### 6.在进行选择类目的时候，如果该模版的类目存在模版，提示用户创建，如果不存在，可以创建模版
> 请求方式：GET<br>
请求URL ：[http://manage.taotao.com/rest/item/param/itemId](#)

### 6.2 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
itemId       |long        |商品的类目ID
### 6.3返回结果
```
{
    "created":1433476785000,
    "updated":1433476785000,
    "id":3,
    "itemCatId":298,
    "paramData":"[{"group":"g1","params":["aa","bb","cc"]},{"group":"g2","params":["ad","sd"]},{"group":"g3","params":["sdd","sdfs","dfg"]}]"
}
```

### 7.更新商品的信息
1. 先加载商品的详情信息
2. 加载商品的描述信息
[http://manage.taotao.com/rest/item/desc/143771131488385](#)
```
{
    "created":1537238230000,
    "updated":1537240520000,
    "itemId":143771131488385,
    "itemDesc":"出现异常的数据"
}
```
3. 根据商品类目的id，加载商品的规格参数的数据
[http://manage.taotao.com/rest/item/param/item/143771131488385](#)
```
{
    "created":1537240473000,
    "updated":1537240526000,
    "id":13,
    "itemId":143771131488385,
    "paramData":"[{"group":"g1","params":[{"k":"aa","v":"a"},{"k":"bb","v":"b"},{"k":"cc","v":"c"}]},{"group":"g2","params":[{"k":"ad","v":"ad"},{"k":"sd","v":"sd"}]},{"group":"g3","params":[{"k":"sdd","v":"sdd"},{"k":"sdfs","v":"sdfs"},{"k":"dfg","v":"dfg"}]}]"
}
```


## 搭建前台系统
### 前台获取商品分类的数据
[http://manage.taotao.com/rest/api/item/cat?callback=category.getDataService](#)
解决了跨域的问题
```
{
    "data":[
        {
            "u":"/products/1.html",
            "n":"<a href='/products/1.html'>图书、音像、电子书刊</a>",
            "i":[
                {
                    "u":"/products/2.html",
                    "n":"电子书刊",
                    "i":[
                        "/products/3.html|电子书",
                        "/products/4.html|网络原创",
                        "/products/5.html|数字杂志",
                        "/products/6.html|多媒体图书"
                    ]
                },
	......
```

### 8.前台系统的广告位的显示
## 引申出内容分类的管理和内容管理

> 请求方式：GET<br>
请求URL ：[http://manage.taotao.com/rest/content/category/parentId](#)

### 8.2 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
parentId       |long        |父节点的ID
### 8.3返回结果
```
[
    {
        "created":1428051098000,
        "updated":1428051100000,
        "id":30,
        "parentId":0,
        "name":"淘淘商城",
        "status":1,
        "sortOrder":1,
        "isParent":true,
        "state":"closed",
        "text":"淘淘商城"
    }
]
```

## 9 新增节点
> 请求方式：POST<br>
请求URL ：[http://manage.taotao.com/rest/content/category](#)

### 请求参数：
字段       |字段类型       |字段说明
------------|-----------|-----------
parentId       |long        |父节点的ID
name       |string        |新增节点的名称

### 注意：
1. 新增内容分类节点的数据
2. 确保该节点的父节点的isparent为true

