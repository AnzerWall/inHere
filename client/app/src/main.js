import Vue from 'vue'
import App from './app'
import  VueRouter from 'vue-router'
import RouteConfig from './router.js'
import RedirectConfig from './redirect.js'
import VueLazyload from 'vue-lazyload'
import  Api from './api/api.js'
import VueSocketio from 'vue-socket.io'; // for ES6
Vue.use(VueSocketio, '115.28.67.181:8360');
//Vue.use(VueSocketio, 'http://172.18.2.32:8360');
//Vue.use(VueSocketio, 'http://127.0.0.1:8360');

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
router.beforeEach(function (transition) {
  let store=router.app.$store;
  if(transition.to.path=='/test'){
    transition.next();
    return;
  }
  if(transition.to.name!="login"&&store.state.user.is_login===false){
    transition.redirect({
      name: 'login',
      query: {
        __ref:transition.to.path
      }
    })

  }else{
    transition.next()
  }

});
//启动
router.start(App, '#app');
