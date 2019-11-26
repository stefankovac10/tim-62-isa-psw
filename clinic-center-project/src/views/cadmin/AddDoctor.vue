<template>
  <div>
    <RegisterForm v-bind:doc="true" v-on:register="registerDoc($emit)"></RegisterForm>
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
      name: undefined,
      surname: undefined,
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
          alert(response.data.email);
        })
        .catch(function(error) {
          alert(error.response);
        });
      this.$router.push("/cadmin");
    }
  }
};
</script>