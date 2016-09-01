/**
 * Created by anzer on 2016/8/29.
 */
import Index from 'pages/index.vue'
import Main from 'pages/Main.vue'
import Other from 'pages/other.vue'
import Demand from 'pages/main/demand.vue'
import Square from 'pages/main/square.vue'
import Message from 'pages/main/message.vue'
import Profile from 'pages/main/profile.vue'
import Home from 'pages/home.vue'


export default{
  '/': {
    component: Main,
    subRoutes: {
      '/': {
        component: Index
      },
      '/demand': {
        component: Demand
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
  '/other': {
    component: Other
  }
}
