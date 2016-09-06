import Vue from 'vue'
import App from './app'
import  VueRouter from 'vue-router'
import RouteConfig from './router.js'
import RedirectConfig from './redirect.js'
import VueLazyload from 'vue-lazyload'
import  Api from './api/api.js'

//配置懒加载插件
Vue.use(VueLazyload);
//加载项目的$api
Vue.use(Api);
//消除移动端点击延迟
const FastClick = require('fastclick');
FastClick.attach(document.body);





//配置路由
Vue.use(VueRouter);
const router = new VueRouter();
router.map(RouteConfig);
router.redirect(RedirectConfig);
//启动
router.start(App, '#app');
