<template>
  <div class="d-flex p-2 justify-content-center">
    <form accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Log in</h1>

      <label class="p-2">E-mail</label>
      <input type="text" class="p-2" id="email" v-model="email" />
      <label class="p-2" for="password">Password</label>
      <input type="password" class="p-2" id="password" v-model="password" placeholder="Password" />
      <br />
      <button type="submit" class="btn btn-primary" v-on:click.prevent="login">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  name: "login",
  props: {
    // props
  },
  data: function() {
    return {
      email: undefined,
      password: undefined
    };
  },
  methods: {
    login: function() {
      let user = {
        email: this.email,
        password: this.password
      };
      httpClient
        .post("/auth/login", user)
        .then(response => {
          localStorage.setItem("User-token", response.data.accessToken);
          localStorage.setItem("Expiary", response.data.expiresIn);
          localStorage.setItem("Email", response.data.email);
          localStorage.setItem("Authority", response.data.authority);
          let role = response.data.authority;
          let path;
          if (role === "ROLE_CCADMIN") {
            path = "/ccadmin";
          } else if (role === "ROLE_CADMIN") {
            path = "/cadmin";
          } else if (role === "ROLE_DOCTOR") {
            path = "/doc";
          } else if (role === "ROLE_NURSE") {
            path = "/nurse";
          } else if (role === "ROLE_PATIENT") {
            path = "/patient";
          } else if (role === "ROLE_REQUEST") {
            path = "/login";
            alert("You hove to be accepted by admin to log in.");
          }
          this.$router.push(path);
        })
        .catch(error => {
          alert(error);
        });
    }
  }
};
</script>
