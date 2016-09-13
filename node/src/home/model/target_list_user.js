'use strict';
import Base from  './base.js'
/**
 Field           Type          Collation        Null    Key     Default  Extra           Privileges                       Comment
 --------------  ------------  ---------------  ------  ------  -------  --------------  -------------------------------  ----------------------------
 id              int(11)       (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  主键
 target_list_id  int(11)       (NULL)           NO              (NULL)                   select,insert,update,references  清单列表编号
 user_id         varchar(100)  utf8_general_ci  NO              (NULL)                   select,insert,update,references  用户编号
 process         double        (NULL)           YES             (NULL)                   select,insert,update,references  任务进度
 has_give_up     tinyint(4)    (NULL)           YES             (NULL)                   select,insert,update,references  是否放弃，0-否，1-是
 is_pass         tinyint(4)    (NULL)           YES             (NULL)                   select,insert,update,references  是否通过，0-未，1-已
 */
export default class extends Base {


}