# SuperDecoration
[ ![Download](https://api.bintray.com/packages/staticsadhu/android/SuperDecoration/images/download.svg?version=1.0.1) ](https://bintray.com/staticsadhu/android/SuperDecoration/1.0.1/link) [![Build Status](https://travis-ci.org/sh1tge/SuperDecoration.svg?branch=master)](https://travis-ci.org/sh1tge/SuperDecoration)

快速设置`LinearLayoutManager`,`GridLayoutManager`item间间距

# 特性
1. 支持单行或当列情况
2. 支持`reverseLayout==true`情况
3. 支持`GridLayoutManager`设置了`SpanSizeLookup`情况
4. 灵活设置各种间距


# sample截图

| LinearLayoutManager        |     normal GridLayoutManager      | GridLayoutManager with Span and reverseLayout   |
| :-------------: |:-------------:| :-------------:|
|<img src="http://blog.wecando.cc/image/20190701/FplFo6vP5gGbdJg-FRcomOFTc-Vy.png" width="540"/>|<img src="http://blog.wecando.cc/image/20190701/FoJW9iWBej5g7h-w0_c1U_pF-wmm.png" width="540"/>|<img src="http://blog.wecando.cc/image/20190701/FgMJJIIUzWEU7k4TpsezXdbeBMX8.png" width="540"/>|

# 使用实例

1. 引用库

```xml
  // 1.添加jcenter仓库
    allprojects {
        repositories {
           jcenter()
        }
    }
    // 2.添加项目依赖（last-version替换为最新版本号）
    dependencies {
        implementation "cc.wecando:SuperDecoration:${last-version}"
    }
```
2. 使用

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

| 名称        |     说明      | 
| :-------------: |:-------------:|
|setPrimarySpace|  设置与orientation同方向,RecyclerView内间距|
|setSecondarySpace| 设置与orientation垂直方向,RecyclerView内间距|
|setPrimaryEdgeSpace| 设置与orientation同方向,ItemView的间距|
|setSecondaryEdgeSpace| 设置与orientation垂直方向,ItemView的间距, LinearLayoutManager无效|
