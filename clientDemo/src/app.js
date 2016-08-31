var Vue = require('vue');
var VueRouter = require('vue-router');

Vue.use(VueRouter)


// var mint = require('mint-ui');
// var Swipe = mint.Swipe;
// var SwipeItem =mint.SwipeItem;
// Vue.component(Swipe.name, Swipe);
// Vue.component(SwipeItem.name, SwipeItem);


Vue.config.debug = true

var App = require('./App.vue');

var router = new VueRouter()

router.map({
    '/demand': {
        component: require('./pages/Demand.vue'),
        subRoutes:{
            '/task':{component:require('./pages/Demand/Task.vue')},
            '/lost':{component:require('./pages/Demand/Lost.vue')},
            '/dating':{component:require('./pages/Demand/Dating.vue')},
        }
    },
    '/square': {
        component: require('./pages/Square.vue'),
    },
    '/message': {
        component: require('./pages/Message.vue'),
    },
    '/profile': {
        component: require('./pages/Profile.vue'),
    }
})

router.redirect({
    '*': '/demand',
    '/demand':'/demand/task',
    '/demand/*':'/demand/task'
})

router.beforeEach(function () {
  window.scrollTo(0, 0)
})

router.start(App, 'app')
