<template>
  <div>
    <RegisterForm v-bind:doc="true" v-on:register="registerDoc(arguments)"></RegisterForm>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import RegisterForm from "@/components/RegisterForm.vue";

export default {
  components: {
    RegisterForm
  },
  data: function() {
    return {
      password: undefined,
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      email: undefined,
      clinic: undefined
    };
  },
  methods: {
    registerDoc: function(args) {
      if (args[1] === "Nurse") {
        httpClient
          .post("/nurse", args[0])
          .then(function(response) {
            response;
          })
          .catch(function(error) {
            error;
          });
        this.$router.push("/cadmin/doctors");

        return;
      }

      httpClient
        .post("/users/doc/" + args[2], args[0])
        .then(function(response) {
          this.response = response;
        })
        .catch(function(error) {
          error;
        });
      this.$router.push("/cadmin/doctors");
    }
  }
};
</script>