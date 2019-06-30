package cc.sadhu.neteasecloudmusic.view.derocation

data class SpaceInfoData(
    val primarySpace: Float, // item间的间距
    val secondarySpace: Float, // layoutManager为gridLayoutManager时, 与orientation垂直方法的item间距
    val primaryEdgeSpace: Float, // 与orientation方向一致的内间距
    val secondaryEdgeSpace: Float // 与orientation垂直方法的内间距

)