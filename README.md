# SuperDecoration
[ ![Download](https://api.bintray.com/packages/staticsadhu/android/SuperDecoration/images/download.svg?version=1.0.3) ](https://bintray.com/staticsadhu/android/SuperDecoration/1.0.3/link) [![Build Status](https://travis-ci.org/sh1tge/SuperDecoration.svg?branch=master)](https://travis-ci.org/sh1tge/SuperDecoration)
快速设置`RecyclerView`item间间距, 配合此篇文章食用,味道更佳[戳我](https://www.wecando.cc/article/9)

# 特性
1. 支持`LinearLayoutManager`,`GridLayoutManager`
2. 支持单行或单列情况
3. 支持`reverseLayout==true`情况
4. 支持`GridLayoutManager`设置了`SpanSizeLookup`情况
5. 灵活设置各种间距


# sample截图

| LinearLayoutManager        |     normal GridLayoutManager      | GridLayoutManager with Span and reverseLayout   |
| :-------------: |:-------------:| :-------------:|
|<img src="https://github.com/Easy-Ez/SuperDecoration/blob/master/images/1.png" width="540"/>|<img src="https://github.com/Easy-Ez/SuperDecoration/blob/master/images/2.png" width="540"/>|<img src="https://github.com/Easy-Ez/SuperDecoration/blob/master/images/3.png" width="540"/>|

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
                .setMainAxisEdgeSpace(12F) // 设置与orientation同方向,RecyclerView内间距,orientation为vertical时, 表示 paddingTop,paddingBottom
                .setCrossAxisEdgeSpace(10F)// 设置与orientation垂直方向,RecyclerView内间距,orientation为vertical时, 表示 paddingLeft,paddingRight
                .setMainAxisSpace(8F)// 设置与orientation同方向,ItemView的间距
                .setCrossAxisSpace(4F)// 设置与orientation垂直方向,ItemView的间距, LinearLayoutManager无效
                .build()
        )

```

# 主要Api

| 名称        |     说明      | 
| :-------------: |:-------------:|
|setMainAxisEdgeSpace|  设置与orientation同方向,RecyclerView内间距|
|setCrossAxisEdgeSpace| 设置与orientation垂直方向,RecyclerView内间距|
|setMainAxisSpace| 设置与orientation同方向,ItemView的间距|
|setCrossAxisSpace| 设置与orientation垂直方向,ItemView的间距, LinearLayoutManager无效|
|setShowDividers|  设置显示分割线模式类似于LinearLayout#setShowDividers方法,SHOW_DIVIDER_NONE = 0,SHOW_DIVIDER_BEGINNING = 1,SHOW_DIVIDER_MIDDLE = 2,SHOW_DIVIDER_END = 4|
|setPaddingLeft| 设置分割线paddingLeft|
|setPaddingRight| 设置分割线paddingRight|
|setDividerColor| 设置分割线前景颜色|
|setBackgroundColor| 设置分割线背景颜色|
