<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Add new</h1>
      <br />

      <label class="p-2">First name</label>
      <input type="text" class="p-2" id="firstName" name="firstName" v-model="firstName" />

      <label class="p-2">Last name</label>
      <input type="text" class="p-2" id="lastName" name="lastName" v-model="lastName" />

      <label class="p-2">JMBG</label>
      <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg" />

      <label class="p-2">Telephone</label>
      <input type="text" class="p-2" id="telephone" name="telephone" v-model="telephone" />

      <label class="p-2">Country</label>
      <input type="text" class="p-2" id="country" name="country" v-model="country" />

      <label class="p-2">City</label>
      <input type="text" class="p-2" id="city" name="city" v-model="city" />

      <label class="p-2">Address</label>
      <input type="text" class="p-2" id="address" name="address" v-model="address" />

      <label for="staticEmail" class="p-2">E-mail</label>
      <input
        type="email"
        class="p-2"
        id="email"
        name="email"
        v-model="email"
        aria-describedby="emailHelp"
        placeholder="Enter email"
      />

      <label class="p-2" for="password">Password</label>
      <input
        type="password"
        class="p-2"
        id="password"
        name="password"
        v-model="password"
        placeholder="Password"
      />

      <label class="p-2" for="confirmPassword">Confirm password</label>
      <input
        type="password"
        class="p-2"
        id="confirmPassword"
        name="confirmPassword"
        v-model="confirmPassword"
        placeholder="Confirm password"
      />
      <label class="p-2" for="matching" id="matching">{{matching}}</label>

      <div v-if="doc" class="d-flex flex-column">
        <label class="p-2">Type</label>
        <select class="p-2" id="type" name="type" v-model="type">
          <option>Doctor</option>
          <option>Nurse</option>
        </select>
        <div class="d-flex flex-column" v-if="type==='Doctor'">
          <label for="tyepOfExamination">Specialised type of examination</label>
          <select name="typeOfExamination" id="typeEx" v-model="typeOfExamination">
            <option v-for="t in types" v-bind:key="t.key">{{t.name}}</option>
          </select>
        </div>
      </div>

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="register">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  name: "registerForm",
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
      matching: "",
      type: undefined,
      types: undefined,
      typeOfExamination: undefined
    };
  },
  props: {
    doc: Boolean,
    user: Object
  },
  watch: {
    confirmPassword() {
      if (this.confirmPassword != this.password)
        this.matching = "Passwords are not matching!";
      else this.matching = "";
    }
  },
  mounted() {
    httpClient
      .get("/types/all")
      .then(response => {
        this.types = response.data;
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    register: function() {
      var user = {
        firstName: this.firstName,
        lastName: this.lastName,
        jmbg: this.jmbg,
        password: this.password,
        email: this.email,
        address: this.address,
        city: this.city,
        country: this.country,
        telephone: this.telephone
      };

      this.$emit("register", user, this.type, this.typeOfExamination);
    }
  }
};
</script>

<style scoped>
#matching {
  color: red;
}
</style>