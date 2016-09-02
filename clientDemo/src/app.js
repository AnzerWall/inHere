var Vue = require('vue');
var VueRouter = require('vue-router');

Vue.use(VueRouter)

Vue.config.debug = true

var App = require('./App.vue');



// var mint = require('mint-ui');
// var Swipe = mint.Swipe;
// var SwipeItem = mint.SwipeItem;

// Vue.component(Swipe.name, Swipe);
// Vue.component(SwipeItem.name, SwipeItem);

var router = new VueRouter()

router.map({
    '/main':{
        component:require('./pages/Main.vue'),
        subRoutes:{
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
        }
    },
    '/demand/detail/:type':{
        component: require('./pages/Demand/Detail.vue')
    },
    // '/demand/lost/:id':{
    //     component: require('./pages/Demand/LostDetail.vue')
    // },
    // '/demand/dating/:id':{
    //     component: require('./pages/Demand/DatingDetail.vue')
    // },
})

router.redirect({
    '*': '/main/demand/task',
    '/main/*':'/main/demand/task'
})

router.beforeEach(function () {
  window.scrollTo(0, 0)
})

router.start(App, 'app')
