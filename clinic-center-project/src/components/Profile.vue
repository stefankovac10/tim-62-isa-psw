<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2" v-bind="componentKey">
      <h1>Clinic administrator</h1>
      <button type="button" class="btn btn-primary" v-on:click="editProfile">Edit profile</button>

      <p>First name: {{ user.firstName }} {{ user.lastName }}</p>
      <p>JMBG: {{ user.jmbg }}</p>
      <p>Telephone: {{ user.telephone }}</p>
      <p>Address: {{ user.address }}, {{ user.city }}, {{ user.country }}</p>
      <p>Email: {{ user.email }}</p>
      <!-- <p>Assigned clinic: {{ user.clinic }}</p> -->
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import EventBus from "@/EventBus.js";

export default {
  data: function() {
    return {
      user: {},
      componentKey: 0
    };
  },
  mounted() {
    EventBus.$on("refresh", function(data) {
      this.user = data;
      this.componentKey += 1;
    });

    httpClient
      .get("/users/profile/1")
      .then(response => {
        this.user = response.data;
      })
      .catch(error => {
        if (error.response.status == 302) this.user = error.response.data;
      });
  },
  methods: {
    editProfile: function() {
      this.$router.push("/cadmin/editProfile/" + this.user.id);
    }
  }
};
</script>
