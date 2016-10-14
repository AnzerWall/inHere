/**
 * Created by anzer on 2016/10/13.
 */
import MainPage from './pages/main.vue';

import DemandListPage from './pages/demand/list.vue'
export default {

  '/main':{
    component: MainPage,
    subRoutes:{
      '/demand':{
        component:DemandListPage,
        name:'demand_list'
      }
    }

  }
}
