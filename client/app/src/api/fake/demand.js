"use strict";
/**
 * Created by anzer on 2016/9/3.
 */
import items from './demand_data'
import MockJs from 'mockjs'
import Comment from './comment.js'
import _ from 'lodash'
let comments = {};
for (let item of items) {
  let num = Math.floor(Math.random() * 20);
  comments[item.id] = Comment.genFakeComment(num);
}

export default{
  fake(mock){
    mock.get('/demand', function (req) {
      let query=req.query||{};
      //user_id
      //ext_type
      //is_end
      //limit
      //offset
      //order_by=  create_time praise
      //order desc asc
      let ret=items;
      if(query.user_id!=undefined)ret=ret.filter(item=>item.user_id===query.user_id);
      if(query.ext_type!=undefined){
        if(!query.ext_type instanceof Array)query.ext_type=[query.ext_type];
        ret= ret.filter(item=>query.ext_type.indexOf(item.ext_type)!=-1);
      }
      if(query.is_end!=undefined){
        ret= ret.filter(item=>query.is_end===item.is_end);
      }
      var count=ret.length;
      let limit=query.limit||10;
      let offset=query.offset||0;
      let end=offset+limit;
      if(offset>=ret.length)ret= [];
      else ret= ret.slice(offset,Math.min(ret.length,end));
      return {
        ok: true,
        body: {
          code: 200,
          status:"success",
          data:{
            page: 0,
            page_size: 0,
            offset: offset,
            limit: limit,
            total: count,
            total_page: 0,
            items:_.cloneDeep(ret)
          }
        }
      };
    });
    mock.get('/demand/:id', function (req) {
      let query=req.query;
      let params=req.params;
      let id= _.toNumber(params.id);

      for(let i=0;i<items.length;i++){
        if(items[i].id===id){
          let ret=_.cloneDeep(items[i]);
          let comment_data=_.cloneDeep(comments[items[i].id]);
          ret.comments={
            page: 0,
            page_size: 0,
            offset: 0,
            limit: 5,
            total: comment_data.length,
            total_page: 0,
            items:comment_data.slice(Math.max(0,comment_data.length-5),5)
          };
          return {
            ok: true,
            body: {
              code: 200,
              status:"success",
              data:ret
            }
          };
        }
      }

      return {
        ok: true,
        body: {
          code: 201,
          status:"id未找到"
        }
      };
    });

  }
}
