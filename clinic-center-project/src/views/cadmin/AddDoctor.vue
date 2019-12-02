<template>
  <div>
    <RegisterForm v-bind:doc="true" v-on:register="registerDoc"></RegisterForm>
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
    registerDoc: function(doc) {
      // var doc = ;

      httpClient
        .post("/users/doc", doc)
        .then(function(response) {
          this.response = response;
        })
        .catch(function(error) {
          alert(error.response);
        });
      this.$router.push("/cadmin");
    }
  }
};
</script>