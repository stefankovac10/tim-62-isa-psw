<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2" v-bind:key="componentKey">
      <h1>Profile</h1>
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
      componentKey: 0,
      id: 0
    };
  },
  mounted() {
    EventBus.$on("refresh", function(data) {
      this.user = data;
      this.componentKey += 1;
    });

    if (localStorage.getItem("Authority") === "ROLE_CADMIN") {
      this.id = 4;
    } else this.id = 13;

    // let email = localStorage.getItem("Email");
    httpClient
      .get("/users/profile/" + this.id)
      .then(response => {
        this.user = response.data;
      })
      .catch(error => {
        if (error.response.status == 302) this.user = error.response.data;
      });
  },
  methods: {
    editProfile: function() {
      this.$router.push("/cadmin/editProfile/" + this.id);
    }
  }
};
</script>
