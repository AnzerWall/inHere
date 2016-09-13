'use strict';
import Base from  './base.js'
/**

 Field           Type          Collation        Null    Key     Default  Extra           Privileges                       Comment
 --------------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  --------------
 id              int(11)       (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  主键
 title           varchar(100)  utf8_general_ci  NO              (NULL)                   select,insert,update,references  标题
 text            varchar(500)  utf8_general_ci  YES             (NULL)                   select,insert,update,references  目标描述
 ext_type        int(11)       (NULL)           YES             (NULL)                   select,insert,update,references  类别
 ext_data        json          (NULL)           YES             (NULL)                   select,insert,update,references  类别数据
 has_finish      tinyint(4)    (NULL)           NO              (NULL)                   select,insert,update,references  是否完成
 target_list_id  int(11)       (NULL)           NO              (NULL)                   select,insert,update,references  所属清单id
 create_time     datetime      (NULL)           YES             (NULL)                   select,insert,update,references  创建时间
 update_time     datetime      (NULL)           YES             (NULL)                   select,insert,update,references  更新时间
 */
export default class extends Base {


}