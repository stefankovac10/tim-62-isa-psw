<template>
  <div>
    <NBHomepage></NBHomepage>
    <h1 class="d-flex p-2 justify-content-center">Welcome to Clinic center</h1>
    <router-view></router-view>
  </div>
</template>

<script>
import NBHomepage from "@/components/NavBarHomepage.vue";

export default {
  components: {
    NBHomepage
  }
};
</script>
