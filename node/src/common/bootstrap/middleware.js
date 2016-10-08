/**
 * this file will be loaded before server started
 * you can register middleware
 * https://thinkjs.org/doc/middleware.html
 */

/**
 * 
 * think.middleware('xxx', http => {
 *   
 * })
 * 
 */
//let cors = require("think-cors");
//think.middleware("cors", cors);
//think.middleware("cors", function(){
//
//});

import cors from '../../home/util/cors.js'
think.middleware("cors", cors);