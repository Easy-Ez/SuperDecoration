# SuperDecoration

快速设置`LinearLayoutManager`,`GridLayoutManager`item间间距

# 特性
1. 支持单行或当列情况
2. 支持`reverseLayout==true`情况
3. 支持`GridLayoutManager`设置了`SpanSizeLookup`情况
4. 支持设置


# sample截图

| LinearLayoutManager        |     normal GridLayoutManager      | GridLayoutManager with Span and reverseLayout   |
| :-------------: |:-------------:| :-------------:|
|<img src="http://blog.wecando.cc/image/20190701/FplFo6vP5gGbdJg-FRcomOFTc-Vy.png" />|||

![](http://blog.wecando.cc/image/20190701/FgMJJIIUzWEU7k4TpsezXdbeBMX8.png)
![](http://blog.wecando.cc/image/20190701/FoJW9iWBej5g7h-w0_c1U_pF-wmm.png)
![](http://blog.wecando.cc/image/20190701/FgMJJIIUzWEU7k4TpsezXdbeBMX8.png)

# 使用实例
```kotlin
        mRvContent.addItemDecoration(
            SuperOffsetDecoration.Builder(layoutManager, this)
                .setPrimarySpace(12F) // 设置与orientation同方向,RecyclerView内间距,orientation为vertical时, 表示 paddingTop,paddingBottom
                .setSecondarySpace(10F)// 设置与orientation垂直方向,RecyclerView内间距,orientation为vertical时, 表示 paddingLeft,paddingRight
                .setPrimaryEdgeSpace(8F)// 设置与orientation同方向,ItemView的间距
                .setSecondaryEdgeSpace(4F)// 设置与orientation垂直方向,ItemView的间距, LinearLayoutManager无效
                .build()
        )

```

# 主要Api


setPrimarySpace  设置与orientation同方向,RecyclerView内间距
setSecondarySpace 设置与orientation垂直方向,RecyclerView内间距
setPrimaryEdgeSpace 设置与orientation同方向,ItemView的间距
setSecondaryEdgeSpace 设置与orientation垂直方向,ItemView的间距, LinearLayoutManager无效
