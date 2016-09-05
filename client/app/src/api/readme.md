在vm里可以直接访问api对象this.$api。

this.$api.demand.listTask(token,query) 获取任务列表

token 暂时固定传递空字符串""
query 传递查询参数对象，目前可能支持以下参数
{
  user_id:过滤用户id,目前假数据里用户id数目为1-10
  id_end:过滤 任务是否结束，传递false，true
  limit：限制返回数量，
  offset：查询的行偏移
  order_by：排序依据，目前不支持
  order：正反序，目前不支持
  ext_type：标签过滤，无需传递
}


返回值，自己尝试
