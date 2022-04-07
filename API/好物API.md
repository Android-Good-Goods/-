---
title: 好物1.0 v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.5"

---

# 好物1.0

> v1.0.0

# 主界面

## HEAD 主界面

HEAD /main

> 返回示例

> 成功

```json
{
  "data": "false"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|string|true|none|none|

# 登录/登录加载界面

## LINK 登录加载

LINK /welcome

> 返回示例

> 成功

```json
{
  "data": "false"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|string|true|none|none|

# 登录/登录界面

## POST 注册

POST /login/signup

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Uaccount|query|string| 否 |用户ID，主键|
|Upwd|query|string| 否 |只能包含数字和字母|
|Uid|query|string| 否 |环信id|
|Unickname|query|string| 否 |none|
|Uphoto|query|string| 否 |头像存储路径|
|Usex|query|string| 否 |1男2女|
|Ubalance|query|string| 否 |账户余额	保留小数点后两位|
|Uaddress|query|string| 否 |地址|
|Uschool|query|string| 否 |学校|
|Ureputation|query|string| 否 |信誉值|
|Utel|query|string| 否 |电话|

> 返回示例

> 成功

```json
{
  "Uaccout": "710000198706145812",
  "Upwd": "D&%ww",
  "Uid": "Y67p&u",
  "Unickname": "小明",
  "Uphote": "http://dummyimage.com/720x300",
  "Usex": 8310573980326868,
  "Ubalance": -8189015074820492,
  "Uaddress": "上海 上海市 长宁区",
  "Uschool": "浙江工商大学",
  "Ureputation": "86",
  "Utel": "18637877681"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Uaccout|string|true|none|none|
|» Upwd|string|true|none|none|
|» Uid|string|true|none|none|
|» Unickname|string|true|none|none|
|» Uphote|string|true|none|none|
|» Usex|integer|true|none|none|
|» Ubalance|number|true|none|none|
|» Uaddress|string|true|none|none|
|» Uschool|string|true|none|none|
|» Ureputation|string|true|none|none|
|» Utel|string|true|none|none|

## GET 登录

GET /login/login

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Uaccount|query|string| 否 |账号|
|Upwd|query|string| 否 |密码|

> 返回示例

> 成功

```json
{
  "Uaccount": "630000200501263954",
  "Upwd": "cWP1dYc"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Uaccount|string|true|none|none|
|» Upwd|string|true|none|none|

# 商品界面/商品分类界面

## OPTIONS 商品列表

OPTIONS /type

> 返回示例

> 成功

```json
{
  "data": "false"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|string|true|none|none|

# 商品界面/商品列表

## PUT 商品列表

PUT /goods/goods_list

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Uid|query|string| 否 |用户ID|
|Uaccount|query|string| 否 |账号|
|Unickname|query|string| 否 |昵称|
|Ureputation|query|string| 否 |信誉值|
|Utel|query|string| 否 |none|
|Gid|query|string| 否 |商品ID|
|Gname|query|string| 否 |商品名|
|Ghownew|query|string| 否 |新旧程度|
|Gprice|query|string| 否 |商品价格|
|Ggetway|query|string| 否 |交易方式，邮寄或自提|
|Goprice|query|string| 否 |商品原价|
|Gdetail|query|string| 否 |商品描述|
|Gaddress|query|string| 否 |发货地址|
|Gscannum|query|string| 否 |浏览人数|
|Gtype|query|string| 否 |商品类型|
|Gstate|query|string| 否 |商品状态，1表示在售，2表示在交易中，3表示交易成功,4表示交易失败，5表示已删除|
|Gimage|query|string| 否 |商品图片存储路径|

> 返回示例

> 成功

```json
{
  "Uid": 44000020031028110,
  "Uaccount": "810000199801012813",
  "Unickname": "(ekZ2",
  "Ureputation": "98",
  "Utel": "19884455218",
  "Gid": 120000200706089650,
  "Gname": "苹果",
  "Ghownew": "4760754109108269",
  "Gprice": 3.2,
  "Ggetway": "邮寄",
  "Goprice": 5,
  "Gdetail": "好还不贵。",
  "Gaddress": "湖南省 株洲市 芦淞区",
  "Gscannum": 8446114175372600,
  "Gtpye": "水果",
  "Gstate": 1,
  "Gimage": "http://dummyimage.com/720x300"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Uid|integer|true|none|none|
|» Uaccount|string|true|none|none|
|» Unickname|string|true|none|none|
|» Ureputation|string|true|none|none|
|» Utel|string|true|none|none|
|» Gid|integer|true|none|none|
|» Gname|string|true|none|none|
|» Ghownew|string|true|none|none|
|» Gprice|number|true|none|none|
|» Ggetway|string|true|none|none|
|» Goprice|number|true|none|none|
|» Gdetail|string|true|none|none|
|» Gaddress|string|true|none|none|
|» Gscannum|integer|true|none|none|
|» Gtpye|string|true|none|none|
|» Gstate|integer|true|none|none|
|» Gimage|string|true|none|none|

# 商品界面/商品细节

## PUT 评论收藏

PUT /goods/goodsdetail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Conid|query|string| 否 |主键，评论id|
|Uaccount|query|string| 否 |none|
|Gid|query|string| 否 |none|
|Uid|query|string| 否 |none|

> 返回示例

> 成功

```json
{
  "Conid": "640000199708203146"
}
```

```json
{
  "Conid": "450000200001312867",
  "Uaccount": "610000197003205833",
  "Gid": 610000199910158300,
  "Uid": 120000202105195700
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Conid|string|true|none|none|
|» Uaccount|string|true|none|none|
|» Gid|integer|true|none|none|
|» Uid|integer|true|none|none|

## GET 加载项获取权限信息

GET /goods/goodsdetail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Uphoto|query|string| 否 |头像存储路径|
|Unickname|query|string| 否 |昵称|
|Ureputation|query|string| 否 |信誉值|

> 返回示例

> 成功

```json
{
  "Uphote": "http://dummyimage.com/728x90",
  "Unickname": "mwXz",
  "Ureputation": 70
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Uphote|string|true|none|none|
|» Unickname|string|true|none|none|
|» Ureputation|integer|true|none|none|

## POST 商品设置

POST /goods/goodsdetail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Gname|query|string| 否 |商品名称|
|Gprice|query|string| 否 |商品价格|
|Goprice|query|string| 否 |商品原价格|
|Gtype|query|string| 否 |商品类型|
|Ghownew|query|string| 否 |新旧程度|
|Ggetway|query|string| 否 |交易方式，邮寄或自提|
|Gdetail|query|string| 否 |商品描述|
|Gimage|query|string| 否 |商品图片存储路径|
|Gstate|query|string| 否 |商品状态，1表示在售，2表示在交易中，3表示交易成功,4表示交易失败，5表示已删除|
|Gscannum|query|string| 否 |浏览人数|
|Gaddress|query|string| 否 |发货地址|

> 返回示例

> 成功

```json
{
  "Gname": "栗子",
  "Gprice": 30,
  "Goprice": 40,
  "Gtype": "情",
  "Ghownew": 81.2,
  "Ggetway": "自取",
  "Gdetail": "始会属开转化个无军以与越联华整面。",
  "Gimage": "http://dummyimage.com/336x280",
  "Gaddress": "青海省 海南藏族自治州 兴海县",
  "Gstate": 3,
  "Gscannum": 617
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Gname|string|true|none|none|
|» Gprice|number|true|none|none|
|» Goprice|number|true|none|none|
|» Gtype|string|true|none|none|
|» Ghownew|number|true|none|none|
|» Ggetway|string|true|none|none|
|» Gdetail|string|true|none|none|
|» Gimage|string|true|none|none|
|» Gaddress|string|true|none|none|
|» Gstate|integer|true|none|none|
|» Gscannum|integer|true|none|none|

# 购买界面/购买细节

## PUT 购买表

PUT /buy/buydetail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Bid|query|string| 否 |求购id	主键|

> 返回示例

> 成功

```json
{
  "Bid": 620000197405286700
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Bid|integer|true|none|none|

## GET 求购商品

GET /buy/buydetail

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Bname|query|string| 否 |求购id	主键|
|Bsprice|query|string| 否 |最低价	保留小数点后两位|
|Bbprice|query|string| 否 |最高价	保留小数点后两位|
|Btype|query|string| 否 |求购类型|
|Bhownew|query|string| 否 |新旧程度|
|Bgetway|query|string| 否 |交易方式|
|Bdetail|query|string| 否 |求购介绍|
|Bimage|query|string| 否 |求购图片	|
|Baddress|query|string| 否 |发布地址|
|Bscannum|query|string| 否 |浏览人数|
|Bstate|query|string| 否 |求购状态，1求购状态，2求购到，3已删除|

> 返回示例

> 成功

```json
{
  "Bname": "2^U",
  "Bsprice": 58,
  "Bbprice": 60,
  "Btype": "玩具",
  "Bhownew": "82.26",
  "Bgetway": "自取",
  "Bdetail": "且与争必些些示感化细红出省九几之除。",
  "Bimage": "http://dummyimage.com/728x90",
  "Baddress": "福建省 福州市 马尾区",
  "Bscannum": 651,
  "Bstate": "1"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Bname|string|true|none|none|
|» Bsprice|number|true|none|none|
|» Bbprice|number|true|none|none|
|» Btype|string|true|none|none|
|» Bhownew|string|true|none|none|
|» Bgetway|string|true|none|none|
|» Bdetail|string|true|none|none|
|» Bimage|string|true|none|none|
|» Baddress|string|true|none|none|
|» Bscannum|integer|true|none|none|
|» Bstate|string|true|none|none|

# 购买界面/订单界面

## GET 订单收发货信息

GET /buy/account

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Aid|query|string| 否 |订单id	主键|
|Gid|query|string| 否 |商品id	外键|
|Uid|query|string| 否 |买家ID     外键|
|Gname|query|string| 否 |none|
|Guid|query|string| 否 |卖家id	外键|
|Abill|query|string| 否 |none|
|Atime|query|string| 否 |交易时间|

> 返回示例

> 成功

```json
{
  "Aid": 310000200107057600,
  "Gid": 140000199608028180,
  "Uid": 33000019740619504,
  "Gname": "Ume9",
  "Guid": 430000199101233900,
  "Abill": 40,
  "Atime": "1994-11-02 PM 22:06:03"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Aid|integer|true|none|none|
|» Gid|integer|true|none|none|
|» Uid|integer|true|none|none|
|» Gname|string|true|none|none|
|» Guid|integer|true|none|none|
|» Abill|number|true|none|none|
|» Atime|string|true|none|none|

## DELETE 删除订单

DELETE /buy/account

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Aid|query|string| 否 |订单id|

> 返回示例

> 成功

```json
{
  "Aid": 450000198607188160
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Aid|integer|true|none|none|

## PUT 上传订单

PUT /buy/account

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Aid|query|string| 否 |订单id	主键|
|Gid|query|string| 否 |商品id	外键|
|Gname|query|string| 否 |商品名称|
|Uid|query|string| 否 |买家id	外键|
|Abill|query|string| 否 |订单金额	保留小数点后两位|
|Atime|query|string| 否 |交易时间|

> 返回示例

> 成功

```json
{
  "Aid": 420000201412242240,
  "Gid": 330000200611062140,
  "Uid": 640000197301214500,
  "Uaccount": "#Wy$Z",
  "Atime": "2001-01-30 AM 10:50:10",
  "Abill": 20
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» Aid|integer|true|none|none|
|» Gid|integer|true|none|none|
|» Uid|integer|true|none|none|
|» Uaccount|string|true|none|none|
|» Atime|string|true|none|none|
|» Abill|number|true|none|none|

# 个人界面/个人信息界面/我的页面

## GET 显示用户信息

GET /user/userInfo

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Uid|query|string| 是 |none|

> 返回示例

> 成功

```json
{
  "data": {
    "Uaccount": "41570",
    "Ubalance": 8756779593830006,
    "Uphoto": "http://dummyimage.com/120x90",
    "Unickname": "称",
    "Uhxid": "539502",
    "Upwd": "yJ&sJp4",
    "Utel": 18134525551,
    "Uaddress": "湖南省 娄底市 娄星区",
    "Uschool": "希望大学",
    "Usex": "男",
    "Ustate": true
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» Uaccount|string|true|none|none|
|»» Upwd|string|true|none|none|
|»» Uhxid|string|true|none|none|
|»» Unickname|string|true|none|none|
|»» Uphoto|string|true|none|none|
|»» Ubalance|integer|true|none|none|
|»» Usex|string|true|none|性别|
|»» Uschool|string|true|none|none|
|»» Uaddress|string|true|none|none|
|»» Utel|integer|true|none|none|
|»» Ustate|boolean|true|none|none|

# 个人界面/个人信息界面/地址管理

## GET 显示地址信息

GET /user/addressInfo

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Username|query|string| 是 |用户名|

> 返回示例

> 成功

```json
{
  "data": {
    "AddressInfo": [
      {
        "AddressID": 1,
        "Receiver": "张三",
        "ReceiverPhone": "15727878399",
        "Address": "浙江省 杭州市 江干区",
        "AddressDetail": "白杨街道浙江工商大学钱江湾生活区",
        "IsdefaultAddress": true
      },
      {
        "AddressID": 2,
        "Receiver": "王五",
        "ReceiverPhone": " 16523838345",
        "Address": "浙江省 杭州市 江干区",
        "AddressDetail": "白杨街道浙江工商大学文科实验楼",
        "IsdefaultAddress": false
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» AddressInfo|[object]|true|none|none|
|»»» AddressID|integer|true|none|none|
|»»» Receiver|string|true|none|none|
|»»» ReceiverPhone|string|true|none|none|
|»»» Address|string|true|none|none|
|»»» AddressDetail|string|true|none|none|
|»»» IsdefaultAddress|boolean|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

## POST 添加地址信息

POST /user/addAddressInfo

> Body 请求参数

```yaml
Receiver: string
ReceiverPhone: string
Address: string
Username: string
AddressDetail: string
IsdefaultAddress: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» Receiver|body|string| 是 |收货人|
|» ReceiverPhone|body|string| 是 |收货手机号|
|» Address|body|string| 是 |所在地区|
|» Username|body|string| 是 |用户名|
|» AddressDetail|body|string| 是 |具体地址|
|» IsdefaultAddress|body|string| 是 |是否设为默认地址|

> 返回示例

> 成功

```json
{
  "data": true
}
```

> 失败

```json
{
  "data": false
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

# 个人界面/设置界面

## PUT 修改昵称

PUT /user/changeNickname

> Body 请求参数

```yaml
Uid: string
Unickname: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» Uid|body|string| 是 |用户id|
|» Unickname|body|string| 是 |输入新的昵称|

> 返回示例

> 成功

```json
{
  "data": true
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

## PUT 修改头像

PUT /user/changePhoto

> Body 请求参数

```yaml
Uid: string
Uphoto: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» Uid|body|string| 是 |用户id|
|» Uphoto|body|string| 是 |修改用户头像|

> 返回示例

> 成功

```json
{
  "data": true
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

# 个人界面/我的收藏

## GET 收藏列表

GET /collect/collect

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Colid|query|string| 否 |收藏id|

> 返回示例

> 成功

```json
{
  "list": [
    {
      "Coltime": "2017-11-21 12:04:03",
      "Uid": "31",
      "Gid": 94,
      "Guid": "21"
    },
    {
      "Gid": 59,
      "Guid": "27",
      "Uid": "7",
      "Coltime": "2016-05-26 02:06:22"
    },
    {
      "Guid": "25",
      "Coltime": "2009-11-22 00:00:58",
      "Gid": 87,
      "Uid": "75"
    },
    {
      "Guid": "85",
      "Uid": "40",
      "Gid": 73,
      "Coltime": "2001-03-16 14:49:53"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» list|[object]|true|none|none|
|»» Gid|integer|false|none|none|
|»» Uid|string|false|none|none|
|»» Guid|string|false|none|none|
|»» Coltime|string|false|none|none|

# 个人界面/订单签收

## PUT 确认收货

PUT /UserInfo/ensureBuyOrder

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|OrderID|query|string| 是 |订单ID|

> 返回示例

> 成功

```json
{
  "data": true
}
```

> 失败

```json
{
  "data": false
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

## GET 显示收货信息

GET /UserInfo/buyOrderInfo

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|UserBuy|query|string| 是 |购买用户|

> 返回示例

> 成功

```json
{
  "data": {
    "Signforgoods": [
      {
        "OrderID": 23,
        "Image": "https://dnsinweidswgwnb",
        "GoodName": "原创设计杭州方言文创",
        "SalePrice": 10
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» Signforgoods|[object]|true|none|none|
|»»» OrderID|integer|false|none|none|
|»»» Image|string|false|none|none|
|»»» GoodName|string|false|none|none|
|»»» SalePrice|integer|false|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

# 发布界面/发布地址/地址管理 Copy

## DELETE 删除地址信息

DELETE /user/deleteAddressInfo

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|AddressID|query|string| 是 |地址ID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

## PUT 修改地址信息

PUT /user/changeAddressInfo

> Body 请求参数

```yaml
Receiver: string
ReceiverPhone: string
Address: string
Username: string
AddressDetail: string
IsdefaultAddress: string
AddressID: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» Receiver|body|string| 是 |收货人|
|» ReceiverPhone|body|string| 是 |收货手机号|
|» Address|body|string| 是 |所在地区|
|» Username|body|string| 是 |用户名|
|» AddressDetail|body|string| 是 |具体地址|
|» IsdefaultAddress|body|string| 是 |是否设为默认地址|
|» AddressID|body|string| 是 |地址ID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

# 发布界面/我的购买

## GET 显示购买列表

GET /message/buyInfo

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|UserBuy|query|string| 是 |购买用户s|

> 返回示例

> 成功

```json
{
  "data": {
    "list": [
      {
        "OrderID": 2,
        "SaleOrBuyID": 23,
        "UserSale": "一闪一闪亮晶晶",
        "GoodName": "原创设计杭州方言文创"
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» list|[object]|true|none|none|
|»»» OrderID|integer|false|none|none|
|»»» SaleOrBuyID|integer|false|none|none|
|»»» UserSale|string|false|none|none|
|»»» GoodName|string|false|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

## GET 我的购买物流

GET /message/showBuyInfo

> Body 请求参数

```yaml
{}

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|OrderID|query|string| 是 |订单ID|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "data": {
    "Image": "https://dsiweiwnbreb",
    "Avatar": "https://nianeownboweb",
    "Nickname": "一闪一闪亮晶晶",
    "SalePrice": 10,
    "Logisticstatus": [
      {
        "Info": "物品已付款"
      },
      {
        "Info": "卖家已发货"
      },
      {
        "Info": "物品已到达，请及时收货"
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» Image|string|true|none|none|
|»» Avatar|string|true|none|none|
|»» Nickname|string|true|none|none|
|»» SalePrice|integer|true|none|none|
|»» Logisticstatus|[object]|true|none|none|
|»»» Info|string|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

# 发布界面/我的转卖

## GET 显示转卖列表

GET /message/saleInfo

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|UserSale|query|string| 是 |出售用户|

> 返回示例

> 成功

```json
{
  "data": {
    "list": [
      {
        "OrderID": 21,
        "UserBuy": "不想起的ming",
        "SaleOrBuyID": 8,
        "GoodName": "微软平板电脑二合一"
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» list|[object]|true|none|none|
|»»» OrderID|integer|false|none|none|
|»»» UserBuy|string|false|none|none|
|»»» SaleOrBuyID|integer|false|none|none|
|»»» GoodName|string|false|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

## GET 我的转卖物流

GET /message/showSaleInfo

> Body 请求参数

```yaml
OrderID: string
SaleOrBuyID: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|OrderID|query|string| 是 |订单ID|
|body|body|object| 否 |none|
|» OrderID|body|string| 是 |订单ID|
|» SaleOrBuyID|body|string| 是 |商品ID|

> 返回示例

> 成功

```json
{
  "data": {
    "Image": "https://dsiwedebtiwnbreb",
    "Avatar": "https://nianefeteownboweb",
    "Nickname": "不想起的ming",
    "SalePrice": 5080,
    "Logisticstatus": [
      {
        "Info": "买家已付款，请及时发货"
      },
      {
        "Info": "您已确定发货"
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» Image|string|true|none|none|
|»» Avatar|string|true|none|none|
|»» Nickname|string|true|none|none|
|»» SalePrice|integer|true|none|none|
|»» Logisticstatus|[object]|true|none|none|
|»»» Info|string|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

# 聊天界面/聊天消息

## GET 显示聊天记录

GET /message/specificChat

> Body 请求参数

```yaml
Username: string
OtherUser: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» Username|body|string| 是 |聊天一方用户|
|» OtherUser|body|string| 是 |聊天另一方用户|

> 返回示例

> 成功

```json
{
  "data": {
    "list": [
      {
        "Avatar": "https://siwneiwnbreb",
        "Message": "请问还有多长时间发货呢~"
      },
      {
        "Avatar": "https://siwneiwderhenbreb",
        "Message": "好的，三天内为您发货"
      }
    ]
  }
}
```

> 失败

```json
{
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|
|»» list|[object]|true|none|none|
|»»» Avatar|string|true|none|none|
|»»» Message|string|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|object|true|none|none|

## POST 发送聊天消息

POST /message/sendMsg

> Body 请求参数

```yaml
Username: string
OtherUser: string
Message: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» Username|body|string| 是 |聊天一方用户|
|» OtherUser|body|string| 是 |聊天另一方用户|
|» Message|body|string| 是 |消息|

> 返回示例

> 成功

```json
{
  "data": true
}
```

> 失败

```json
{
  "data": false
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

状态码 **400**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» data|boolean|true|none|none|

# 数据模型

