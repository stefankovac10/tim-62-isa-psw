<template>
  <div>
    <PHomepage></PHomepage>
    <h1 class="d-flex p-2 justify-content-center">Patient's homepage</h1>
    <router-view></router-view>
  </div>
</template>

<script>
import PHomepage from "@/components/NavBarPatient.vue";

export default {
  components: {
    PHomepage
  }
};
</script>