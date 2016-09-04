"use strict";
/**
 * Created by anzer on 2016/9/3.
 */
export default function (mock){
  mock.get('/demand', function(req) {
    console.log(req);
    return {
      body:{

      }
    };
  });

}
