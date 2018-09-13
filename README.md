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

### 新增商品
### 1.1 功能描述
新增某一个类目下的商品数据
### 1.2 请求说明
> 请求方式：POST<br>
请求URL ：[http://localhost:8081/rest/item/](#)

### 1.3 请求参数(序列化成字符串)
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
### 1.4 返回结果
```
｛"code":201｝
```

+ 由于商品描述信息比较大，故为了查询的性能，将商品详情和商品描述，分开存放到不同的表
故此，需要考虑到事务的问题。

### 上传图片
+ 验证图片的后缀名是否正确
+ 获取上传图片路径，并目标路径
+ 生成图片的绝对的引用地址
+ 将图片写入到磁盘
+ 检验图片是否合法





