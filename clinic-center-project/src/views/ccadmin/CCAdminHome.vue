<template>
  <div>
    <NBCCAdmin></NBCCAdmin>
    <router-view></router-view>
  </div>
</template>

<script>
import NBCCAdmin from "@/components/NavBarCCAdmin.vue";

export default {
  components: {
    NBCCAdmin
  }
};
</script>
