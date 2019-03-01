## 1、访问地址：

列表查询：

http://localhost:8094/dubboTwo/item/list

分页查询：

http://localhost:8094/dubboTwo/item/page/list?pageNo=1&pageSize=4

带参数的模糊查询：

http://localhost:8094/dubboTwo/item/page/list/params?pageNo=1&pageSize=4&search=SpringCloud


用户下单：

（1）http://localhost:9013/v1/record/push

请求参数：

```
{
	"itemId":-1,
	"total":5,
	"customerName":"debug"
}
```

（2）http://localhost:8094/dubboTwo/order/record/push


请求参数：

```
{
	"itemId":-1,
	"total":5,
	"customerName":"debug"
}
```
