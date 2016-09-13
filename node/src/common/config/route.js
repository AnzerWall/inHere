/**
 * Created by anzer on 2016/9/12.
 */
export default [
    ["nuo/:id/praise",{
        post:"nuo/praise"
    }],
    ["nuo/:id",{
        post:"nuo/update"
    }],
    ["nuo",{
        post:"nuo/create",
        get:"nuo/get_list"
    }],

];