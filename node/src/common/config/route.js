/**
 * Created by anzer on 2016/9/12.
 */
export default [
    ["test",{
        get:"index/test"
    }],
    ["chat",{
        post:'chat/send'
    }],
    ["nuo/:id/praise",{
        post:"nuo/praise"
    }],
    ["nuo/:id/low",{
        post:"nuo/low"
    }],
    ["nuo/:id/participator",{
        post:"nuo/join_it"
    }],
    ["nuo/:id/abnegator",{
        post:"nuo/give_up"
    }],
    ["nuo/:id",{
        post:"nuo/update",
        get:"nuo/get"
    }],
    ["nuo",{
        post:"nuo/create",
        get:"nuo/get_list"
    }],

];