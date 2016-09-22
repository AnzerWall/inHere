/**
 * Created by anzer on 2016/8/31.
 */
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);
import user from './modules/user.js'
import chat from './modules/chat.js'
export default new Vuex.Store({
  modules: {
    user,
    chat
  }

})
