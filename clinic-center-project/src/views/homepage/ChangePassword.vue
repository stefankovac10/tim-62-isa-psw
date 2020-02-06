<template>
  <div class="d-flex p-2 justify-content-center">
    <form accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Change password</h1>
      <label class="p-2" for="password">Old password</label>
      <input
        type="password"
        class="p-2"
        id="oldPassword"
        v-model="oldPassword"
        placeholder="Password"
      />
      <br />
      <label class="p-2" for="password">New password</label>
      <input type="password" class="p-2" id="newPassword" v-model="password" placeholder="Password" />
      <br />
      <label class="p-2" for="password">Repeat password</label>
      <input
        type="password"
        class="p-2"
        id="confirmPassword"
        v-model="confirmPassword"
        placeholder="Password"
      />
      <label class="p-2" for="matching" id="matching">{{matching}}</label>
      <br />
      <button type="submit" class="btn btn-primary" v-on:click.prevent="change">Change</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
//import moment from "moment";

export default {
  name: "login",
  props: {
    // props
  },
  watch: {
    confirmPassword() {
      if (this.confirmPassword != this.password)
        this.matching = "Passwords are not matching!";
      else this.matching = "";
    }
  },
  data: function() {
    return {
      password: undefined,
      confirmPassword: undefined,
      oldPassword: undefined,
      matching: ""
    };
  },
  methods: {
    change: function() {
      let errorHappened = true;
      if (
        this.password === undefined ||
        this.password === "" ||
        this.confirmPassword === undefined ||
        this.confirmPassword === "" ||
        this.password != this.confirmPassword
      ) {
        this.$vToastify.info({
          body: "Please, enter password",
          title: "Info",
          type: "info",
          canTimeout: true,
          append: false,
          duration: 2000
        });
        return;
      }

      httpClient
        .post("/auth/change-password", {
          oldPassword: this.oldPassword,
          newPassword: this.password
        })
        .then(() => {
          errorHappened = false;
          this.$vToastify.info({
            body: "Password successufully changed",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          errorHappened = true;
          this.$vToastify.info({
            body: "Incorrect old password",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
          return;
        })
        .then(() => {
          if (!errorHappened) {
            let role = localStorage.getItem("Authority");
            let path;
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
            this.$router.push(path);
          }
        });
    }
  }
};
</script>

<style scoped>
#matching {
  color: red;
}
</style>