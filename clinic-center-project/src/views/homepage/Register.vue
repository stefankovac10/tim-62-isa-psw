<template>
  <RegisterForm v-on:register="registerUser"></RegisterForm>
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
    registerUser: function(event) {
      
        if(event.firstName === ''  || event.firstName === undefined || event.lastName === ''  || event.lastName === undefined || event.password === ''  || event.password === undefined
          || event.email === ''  || event.email === undefined || event.jmbg === ''  || event.jmbg === undefined || event.telephone === ''  || event.telephone === undefined
          || event.country === ''  || event.country === undefined || event.city === ''  || event.city === undefined || event.address === ''  || event.address === undefined){
              this.$vToastify.info({
              body: "Please, fill all the information",
              title: "Info",
              type: "info",
              canTimeout: true,
              append: false
            });
              return;
        }
        httpClient
        .post("/regrequest", event)
        .then(response => {
          this.response = response;
          this.$vToastify.info({
              body: "Your registration request have been sent, please wait email with information",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false
            });
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