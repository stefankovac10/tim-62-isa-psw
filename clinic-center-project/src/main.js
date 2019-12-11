import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "bootstrap";
import "bootswatch/dist/slate/bootstrap.min.css";
import VueToastify from 'vue-toastify';
import YandexMap from "vue-yandex-map";

Vue.use(YandexMap);
Vue.use(VueToastify);
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
