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
import moment from "moment";

export default {
  name: "login",
  props: {
    // props
  },
  data: function() {
    return {
      email: undefined,
      password: undefined,
      firstTime: undefined
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
          localStorage.setItem("Refresh-token", response.data.refreshToken);
          localStorage.setItem("Email", response.data.email);
          localStorage.setItem("Authority", response.data.authority);

          let time = moment().add(response.data.expiresIn);
          localStorage.setItem("Expiary", time);

          let role = response.data.authority;
          let path;

          httpClient
            .get("/users/" + response.data.email)
            .then(response => {
              this.response = response;
              this.firstTime = this.response.data;

              if (this.firstTime === true && role != "ROLE_PATIENT") {
                this.$router.push("/changePassword");
              } else {
                if (role === "ROLE_CCADMIN") {
                  path = "/ccadmin";
                } else if (role === "ROLE_CADMIN") {
                  path = "/cadmin/profile";
                } else if (role === "ROLE_DOCTOR") {
                  path = "/doc/profile";
                } else if (role === "ROLE_NURSE") {
                  path = "/nurse/profile";
                } else if (role === "ROLE_PATIENT") {
                  path = "/patient/profile";
                } else if (role === "ROLE_REQUEST") {
                  path = "/login";
                  this.$vToastify.info({
                    body: "You hove to be accepted by admin to log in.",
                    title: "Success",
                    type: "success",
                    canTimeout: true,
                    append: false,
                    duration: 2000
                  });
                }
              }
              this.$router.push(path);
              //}
            })
            .catch(error => {
              this.error = error;
            });
        })
        .catch(() => {
          this.$vToastify.info({
            body: "Your username or passwrord are not correct.",
            title: "Warning",
            type: "warning",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
    }
  }
};
</script>
