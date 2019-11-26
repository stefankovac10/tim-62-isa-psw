<template>
  <div>
    <NBCAdmin></NBCAdmin>
    <router-view></router-view>
  </div>
</template>

<script>
import NBCAdmin from "@/components/navs/NavBarCAdmin.vue";

export default {
  components: {
    NBCAdmin
  }
};
</script>
