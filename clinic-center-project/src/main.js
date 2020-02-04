import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap";
import "bootswatch/dist/flatly/bootstrap.min.css";
import VueToastify from 'vue-toastify';
import YandexMap from "vue-yandex-map";
import FullCalendar from 'vue-full-calendar';
import modal from 'vue-js-modal';
Vue.use(FullCalendar)

Vue.use(YandexMap);
Vue.use(VueToastify);
Vue.use(modal, {dialog: true, dynamic: true})
Vue.config.productionTip = false;

import 'fullcalendar/dist/fullcalendar.css'


new Vue({
  router,
  render: h => h(App)
}).$mount("#app");

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!localStorage.getItem("User-token"))
      next("/login");
    else
      next();
  } else {
    next();
  }

})

