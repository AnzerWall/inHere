"use strict";
/**
 * middleware
 */
export default class extends think.middleware.base {
    /**
     * run
     * @return {} []
     */
    run(){

        this.http.header("Access-Control-Allow-Origin", "*");
        this.http.header("Access-Control-Allow-Methods", "GET,PUT,DELETE,POST,OPTION,HEAD,PATCH");
        this.http.header("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
    }
}