<template>
  <div>
    <PHomepage></PHomepage>
    <router-view></router-view>
  </div>
</template>

<script>
import PHomepage from "@/components/navs/NavBarPatient.vue";

export default {
  components: {
    PHomepage
  }
};
</script>