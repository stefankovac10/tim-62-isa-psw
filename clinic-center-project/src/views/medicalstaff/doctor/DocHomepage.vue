<template>
  <div>
    <NBDoc></NBDoc>
    <router-view></router-view>
  </div>
</template>

<script>
import NBDoc from "@/components/navs/NavBarDoc.vue";

export default {
  components: {
    NBDoc
  }
};
</script>