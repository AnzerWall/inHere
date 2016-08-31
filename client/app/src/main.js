import Vue from 'vue'
import App from './app'
import  VueRouter from 'vue-router'
import RouteConfig from './router.js'

//消除移动端点击延迟
const FastClick = require('fastclick');
FastClick.attach(document.body);

//配置路由
Vue.use(VueRouter);
const router = new VueRouter();
router.map(RouteConfig);
//启动
router.start(App, '#app');
