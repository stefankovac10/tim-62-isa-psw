<template>
  <RegisterForm v-on:register="registerUser($emit)"></RegisterForm>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import RegisterForm from "@/components/RegisterForm.vue";

export default {
  name: "register",
  props: {
    // props
  },
  data: function() {
    return {
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      email: undefined,
      password: undefined,
      confirmPassword: undefined,
      matching: ""
    };
  },
  components: {
    RegisterForm
  },
  watch: {
    confirmPassword() {
      if (this.confirmPassword != this.password)
        this.matching = "Passwords are not matching!";
      else this.matching = "";
    }
  },
  methods: {
    registerUser: function(user) {
      httpClient
        .post("/regrequest", user)
        .then(response => {
          this.response = response;
          alert("Your registration request has been sent");
        })
        .catch(error => {
          this.error = error;
        });

      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
#matching {
  color: red;
}
</style>