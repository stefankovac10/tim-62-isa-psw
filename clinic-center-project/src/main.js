import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap";
import "bootswatch/dist/slate/bootstrap.min.css";
import VueToastify from 'vue-toastify';

Vue.use(VueToastify);
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
