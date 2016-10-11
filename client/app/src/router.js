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

import DemandPublish from 'pages/demand/demand-publish.vue'
import TaskPublish from 'pages/demand/task-publish.vue'
import LostPublish from 'pages/demand/lost-publish.vue'
import DatingPublish from 'pages/demand/dating-publish.vue'


import ChatListPage from 'pages/chat/chat-list.vue'
import ChatPage from 'pages/chat/chat.vue'

import  NuoListPage from 'pages/nuo/list.vue'
import  NuoDetailPage from 'pages/nuo/detail.vue'
import  CreateNuoPage from 'pages/nuo/create.vue'

import Cao from 'pages/square/cao.vue'
import Answer from 'pages/square/answer.vue'
import CaoTopic from 'pages/square/cao-topic.vue'
import AnswerTopic from 'pages/square/answer-topic.vue'
import CaoDetail from 'pages/square/cao-detail.vue'
import Label from 'pages/square/label.vue'
import AnswerDetail from 'pages/square/answer-detail.vue'

import CaoPublish from 'pages/cao/cao-publish.vue'
import CaoInPublish from 'pages/cao/in-publish.vue'
import CaoOutPublish from 'pages/cao/out-publish.vue'
import AskPublish from 'pages/cao/ask-publish.vue'

import LoginPage from 'pages/login/login.vue'
import Register from 'pages/register/register.vue'

//import ChatTalk from 'pages/message/chat-talk.vue'
//import NoticeMessage from 'pages/message/notice-message.vue'
import ActivityList from 'pages/square/activity-list.vue'
import ActivityDetails from 'pages/square/activity-details.vue'
export default{
  '/': {
    component: Index

  },
  '/main': {
    component: Main,
    subRoutes: {
      '/': {
        component: Index
      },
      '/demand': {
        component: Demand,
        subRoutes: {
          '/task':{
            name:'task',
            component: DemandTask
          },
          '/lost':{
            name:'lost',
            component: DemandLost
          },
          '/dating':{
            name:'dating',
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
  '/login':{
    component:LoginPage,
    name:"login"
  },
  '/chat-list':{
    component: ChatListPage
  },
  '/chat':{
    component: ChatPage
  },
  '/nuo/:id':{
    component:NuoDetailPage,
   // waitForData:true
  },
  '/nuo':{
    component:NuoListPage
  },
  '/create-nuo':{
    component:CreateNuoPage
  },
  '/demand-publish':{
    component: DemandPublish,
    subRoutes:{
      // '/': {
      //   component: TaskPublish
      // },
      '/task':{
        name:'publish_task',
        component: TaskPublish
      },
      '/lost': {
        name:'publish_lost',
        component: LostPublish
      },
      '/dating': {
        name:'publish_dating',
        component: DatingPublish
      }
    }
  },
  //'/chat-talk/:id':{
  //  component: ChatTalk
  //},
  '/activity':{
    component: ActivityList
  },
  '/activity/:id':{
    component: ActivityDetails
  },
  //'/notice-message/:id':{
  //  component: NoticeMessage
  //},
  // '/notice-message/:id':{
  //   component: NoticeMessage
  // },
  '/cao':{
    component:Cao,
  },
  '/answer':{
    component:Answer
  },
  '/cao-topic':{
    component:CaoTopic
  },
  '/answer-topic':{
    component:AnswerTopic
  },
  '/cao-detail/:id':{
    component:CaoDetail
  },
  '/label':{
    component:Label
  },
  '/answer-detail/:id':{
    component:AnswerDetail
  },
  '/cao-publish':{
    component: CaoPublish,
    subRoutes:{
      '/':{
        component:CaoInPublish
      },
      '/in':{
        name: 'cao_in_publish',
        component: CaoInPublish
      },
      '/out':{
        name: 'cao_out_publish',
        component: CaoOutPublish
      }
    }
  },
  '/ask-publish':{
    component: AskPublish
  },
  '/register':{
    component:Register
  }
}
