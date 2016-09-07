/**
 * Created by anzer on 2016/8/29.
 */
import Index from 'pages/index.vue'
import Main from 'pages/Main.vue'
import Other from 'pages/other.vue'
import Test from 'pages/test.vue'

import Square from 'pages/main/square.vue'
import Message from 'pages/main/message.vue'
import Profile from 'pages/main/profile.vue'
import Home from 'pages/home.vue'

import Demand from 'pages/main/demand.vue'
import DemandDetail from  'pages/demand/detail.vue'
import DemandTask from  'pages/demand/task.vue'
import DemandLost from  'pages/demand/lost.vue'
import DemandDating from  'pages/demand/dating.vue'


import ChatList from 'pages/message/chat-list.vue'
export default{
  '/': {
    component: Main,
    subRoutes: {
      '/': {
        component: Index
      },
      '/demand': {
        component: Demand,
        subRoutes: {
          '/task':{
            component: DemandTask
          },
          '/lost':{
            component: DemandLost
          },
          '/dating':{
            component: DemandDating
          }
        }
      },
      '/square': {
        component: Square
      },
      '/message': {
        component: Message
      },
      '/profile': {
        component: Profile
      }
    }
  },
  '/demand-detail/:id':{
    component:DemandDetail
  },
  '/test':{
    component:Test
  },
  '/chat':{
    component: ChatList
  }
}
