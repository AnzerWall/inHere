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
    ["nuo/:id/target/:index/count",{
        post:"nuo/target_count"
    }],
    ["nuo/:id/target/:index/do",{
        post:"nuo/target_do_todo"
    }],
    ["nuo/:id/target/:index/complete",{
        post:"nuo/target_complete"
    }],
    ["nuo/:id/target/:index/sign-in",{
        post:"nuo/target_sign_in"
    }],
    ["nuo/:id/target/:index/answer",{
        post:"nuo/target_answer"
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
    ["notice",{
        get:"notice/get_list"
    }],


];