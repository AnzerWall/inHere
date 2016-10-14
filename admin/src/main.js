import Vue from 'vue'
import RootPage from './root.vue'
import VueRouter from 'vue-router'
import RouteConfig from './router.js'
import N3Components from 'N3-components'
N3Components.install(Vue)

Vue.use(VueRouter);
const router = new VueRouter();
router.map(RouteConfig);
router.start(RootPage,"#app");
