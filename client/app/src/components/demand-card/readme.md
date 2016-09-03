#实例

        <demand-card
        :is_detail="true"

        :create_time="item.create_time"
        :data="item.data"
        :tag="item.tag"
        @click="onCardTag(item)"
        @view-image="onViewImage">
        </demand-card>


#参数


      is_detail Boolean 是否显示成为详情页的样式
      create_time 创建时间
      tag 标签
      data 标签数据
      @click 绑定点击时间
      @view-image 绑定点击图片事件     回调接收两个参数 index list 表示 第几张图片和图片列表

回调实例

         onViewImage(index,list){//绑定点击图片事件
               //   this.$refs.viewer是图片浏览组件的应用，调用其中show方法
               this.$refs.viewer.show(index,list);
             },

#viewer图片预览的用法
      导入组件 import
      局部注册组件 在viewmodel里声明components:{xxx}
      在页面中加入标签  <photo-swipe v-ref:viewer></photo-swipe>
      为demand-card组件绑定view-image事件
      在时间中调用this.$refs.viewer.show(index,list);

      v-ref 为 给组件 建立一个引用，这样可以通过this.$refs访问到组件
      然后调用组件方法show()传递两个参数
