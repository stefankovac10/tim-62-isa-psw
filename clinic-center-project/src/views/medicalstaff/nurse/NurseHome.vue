<template>
  <div>
    <NHomepage></NHomepage>
    <router-view></router-view>
  </div>
</template>

<script>
import NHomepage from "@/components/navs/NavBarNurse.vue";

export default {
  components: {
    NHomepage
  }
};
</script>