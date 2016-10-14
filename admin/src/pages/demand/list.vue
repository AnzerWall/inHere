<template>
  <div>
        <div class="tab-content">
          <div>

            <n3-select :value.sync="type" :options="type_list">

            </n3-select>
            <n3-select :value.sync="status" :options="status_list">

            </n3-select>
          </div>
          <n3-data-table class="table-content"
                         key="id"
                         :filter="true"
                         :source="list"
                         :columns="columns"
                         :select-col="false"
                         :selection="selection"
                         :on-change="dataChange"
          ></n3-data-table>
        </div>



  </div>
</template>
<style>
  .tab-content {
    /*margin-top: 20px;;*/
  }

  .table-content {
    margin-top: 10px;;
  }
</style>
<script type="text/ecmascript-6">
  import data from './data.js'

  let items = data.data.items;
  let source = [];
  for (let item of items) {
    if (item.ext_type === 1) {
      source.push({
        id: item.id,
        type: item.ext_type,
        text: item.text,
        photos: item.photos,
        user_id: item.user_id,
        pay: item.ext_data.pay || 0,
        is_end: items.is_end ? '已结束' : '未结束',
        create_time: item.create_time,
        update_time: item.edit_time
      })
    } else if (item.ext_type === 2) {
      source.push({
        id: item.id,
        type: item.ext_type,
        text: item.text,
        photos: item.photos,
        user_id: item.user_id,
        price: item.ext_data.price || 0,
        quality: item.ext_data.quality || "",
        buy_time: item.ext_data.buy_time || 0,
        original_price: item.ext_data.original_price || 0,
        is_end: items.is_end ? '已结束' : '未结束',
        create_time: item.create_time,
        update_time: item.edit_time
      })
    } else if (item.ext_type === 3) {
      source.push({
        id: item.id,
        type: item.ext_type,
        text: item.text,
        photos: item.photos,
        user_id: item.user_id,
        pay: item.ext_data.pay || 0,
        is_end: items.is_end ? '已结束' : '未结束',
        create_time: item.create_time,
        update_time: item.edit_time
      })
    } else if (item.ext_type === 4) {
      source.push({
        id: item.id,
        type: item.ext_type,
        text: item.text,
        photos: item.photos,
        user_id: item.user_id,
        thing: item.ext_data.thing || "",
        lose_time: item.ext_data.lose_time || 0,
        is_end: items.is_end ? '已结束' : '未结束',
        create_time: item.create_time,
        update_time: item.edit_time
      })
    } else if (item.ext_type === 5) {
      source.push({
        id: item.id,
        type: item.ext_type,
        text: item.text,
        photos: item.photos,
        user_id: item.user_id,
        thing: item.ext_data.thing || "",
        pickeup_time: item.ext_data.pickeup_time || 0,
        is_end: items.is_end ? '已结束' : '未结束',
        create_time: item.create_time,
        update_time: item.edit_time
      })
    } else if (item.ext_type === 6) {
      source.push({
        id: item.id,
        type: item.ext_type,
        text: item.text,
        photos: item.photos,
        user_id: item.user_id,
        place: item.ext_data.place || "",
        end_time: item.ext_data.end_time || "",
        start_time: item.ext_data.start_time || "",
        want_sex: item.ext_data.want_sex || 0,
        per_cost: item.ext_data.per_cost || 0,
        gathering_time: item.ext_data.gathering_time || 0,
        gathering_place: item.ext_data.gathering_place || "",
        is_end: items.is_end ? '已结束' : '未结束',
        create_time: item.create_time,
        update_time: item.edit_time
      })
    }
  }
  let columns_common = [
    {
      title: '描述',
      dataIndex: 'text',
//      width: '40%'
    }, {
      title: '图片',
      dataIndex: '',
      render:(text,record,index)=>{
        let ret='';
        let photos=record.photos||[];
        for(let i in photos){
          let photo=photos[i];
          ret+= `
        <n3-popover effect="fade"   placement="left" trigger="hover" :header="false">
          <div slot="content">
            <img src="${photo.src}" style="width: 200px;height:200px;">
          </div>
               <n3-button >图片{{${(+i)+1}}}</n3-button>
          </n3-popover>
          `
        }
        return ret;

      }
    }, {
      title: '是否已结束',
      dataIndex: 'is_end',
      filter:true
    }
  ];
  let columns_operator = [
    {
      title: '操作',
      dataIndex: '',
      render: (text, record, index) => {
        return `<span class="item">
                <a href="javascript:;" >通过</a>
                <a href="javascript:;" >不通过</a>
              </span>`
      }
    }
  ]
  let columns_1 = [
    {
      title: '酬金',
      dataIndex: 'pay'
    }
  ];
  let columns_2 = [
    {
      title: '价格',
      dataIndex: 'price'
    },
    {
      title: '成色',
      dataIndex: 'quality'
    },
    {
      title: '购买时间',
      dataIndex: 'buy_time'

    },
    {
      title: '原价',
      dataIndex: 'original_price'
    }
  ];
  let columns_3 = [
    {
      title: '酬金',
      dataIndex: 'pay'
    }
  ];
  let columns_4 = [
    {
      title: '物品',
      dataIndex: 'thing'
    },
    {
      title: '丢失时间',
      dataIndex: 'lose_time'
    }
  ];
  let columns_5 = [
    {
      title: '物品',
      dataIndex: 'thing'
    },
    {
      title: '发现时间',
      dataIndex: 'pickeup_time'
    }
  ];
  let columns_6 = [
    {
      title: '地点',
      dataIndex: 'place'
    },
    {
      title: '人均消费',
      dataIndex: 'per_cost'

    },
    {
      title: '结束时间',
      dataIndex: 'end_time'
    },
    {
      title: '开始时间',
      dataIndex: 'start_time'

    },
    {
      title: '性别要求',
      dataIndex: 'want_sex'
    },
    {
      title: '集中时间',
      dataIndex: 'gathering_time'
    },
    {
      title: '集中地点',
      dataIndex: 'gathering_place'
    }
  ];
  export default{
    methods: {
      dataChange(pagination, query, sort, filter){
        console.log(pagination, query, sort, filter);
      },
      changeType(type = 1){
        console.log(`changeType:${type}`);
        if (type === 1)
          this.columns = columns_common.concat(columns_1).concat(columns_operator);
        else if (type === 2)
          this.columns = columns_common.concat(columns_2).concat(columns_operator);
        else if (type === 3)
          this.columns = columns_common.concat(columns_3).concat(columns_operator);
        else if (type === 4)
          this.columns = columns_common.concat(columns_4).concat(columns_operator);
        else if (type === 5)
          this.columns = columns_common.concat(columns_5).concat(columns_operator);
        else if (type === 6)
          this.columns = columns_common.concat(columns_6).concat(columns_operator);
        this.list=source.filter((item)=>item.type===type)
      }

    },
    route: {
      data(){
        this.type = 1;
      }
    },
    watch: {
      type(newValue, oldValue){
        this.changeType(newValue)
      }
    },
    data(){
      return {
        status:0,
        status_list:[{
          label: '未核审',
          value: 0
        },{
          label: '已核审',
          value: 1
        },{
          label: '已屏蔽',
          value: 2
        }],
        type_list: [{
          label: '快递',
          value: 1
        }, {
          label: '转让',
          value: 2
        }, {
          label: '帮忙',
          value: 3
        }, {
          label: '失物',
          value: 4
        }, {
          label: '捡到',
          value: 5
        }, {
          label: '走起',
          value: 6
        }],
        type: 0,
//        filter_list: [{
//          title: '描述',
//          dataIndex: 'text',
//          options: [{value: "v白", label: "v白"}, {value: "t红", label: "t红"}],
//          value: [],
//        }],
        query: {
          type: 1
        },

        list: [],
        selection: {
          checkRows: [],
          onSelect(a, b, c){
          },
          onSelectAll(){
          },
//          getCheckboxProps(record){
//            if (record.key == 2) {
//              return {
//                disabled: true
//              }
//            } else {
//              return {
//                disabled: false
//              }
//            }
//          }
        },
        columns: columns_common,
        pagination:{
          current:0,
          total:0,
          pagesize:10
        }
      }
    }


  }
</script>
