<template>
  <div class="d-flex p-2 justify-content-center">
    <form accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Change password</h1>
      <label class="p-2" for="password">New password</label>
      <input type="password" class="p-2" id="password" v-model="password" placeholder="Password" />
      <br />
      <label class="p-2" for="password">Repeat password</label>
      <input type="password" class="p-2" id="password" v-model="passwordRepeat" placeholder="Password" />
      <br />
      <button type="submit" class="btn btn-primary" v-on:click.prevent="login">Change</button>
    </form>
  </div>
</template>

<script>
//import { httpClient } from "@/services/Api.js";
//import moment from "moment";

export default {
  name: "login",
  props: {
    // props
  },
  data: function() {
    return {
        password: undefined,
        passwordRepeat: undefined
    };
  },
  methods: {
  }
      
};
</script>
