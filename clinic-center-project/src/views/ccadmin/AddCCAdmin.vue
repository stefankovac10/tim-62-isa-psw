<template>
  <div id="form" class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Add Clinical Center Administrator</h1>
      <br />

      <label class="p-2">Name</label>
      <input
        type="text"
        class="p-2"
        id="firstName"
        name="firstName"
        v-model="firstName"
        placeholder="Enter firstName"
      />

      <label class="p-2">Last name</label>
      <input
        type="text"
        class="p-2"
        id="lastName"
        name="lastName"
        v-model="lastName"
        placeholder="Enter lastName"
      />

      <label class="p-2">JMBG</label>
      <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg" placeholder="Enter jmbg" />

      <label class="p-2">Telephone</label>
      <input
        type="text"
        class="p-2"
        id="telephone"
        name="telephone"
        v-model="telephone"
        placeholder="Enter telephone"
      />

      <label class="p-2">Country</label>
      <input
        type="text"
        class="p-2"
        id="country"
        name="country"
        v-model="country"
        placeholder="Enter country"
      />

      <label class="p-2">City</label>
      <input type="text" class="p-2" id="city" name="city" v-model="city" placeholder="Enter city" />

      <label class="p-2">Address</label>
      <input
        type="text"
        class="p-2"
        id="address"
        name="address"
        v-model="address"
        placeholder="Enter address"
      />

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

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="add">Add</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  name: "addCCAdmin",
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
      password: undefined
    };
  },
  methods: {
    add: function() {
      if(this.firstName === ''  || this.firstName === undefined || this.lastName === ''  || this.lastName === undefined || this.password === ''  || this.password === undefined
          || this.email === ''  || this.email === undefined || this.jmbg === ''  || this.jmbg === undefined || this.telephone === ''  || this.telephone === undefined
          || this.country === ''  || this.country === undefined || this.city === ''  || this.city === undefined || this.address === ''  || this.address === undefined){
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
        .post("/ccadmin", {
          firstName: this.firstName,
          lastName: this.lastName,
          jmbg: this.jmbg,
          password: this.password,
          email: this.email,
          address: this.address,
          city: this.city,
          country: this.country,
          telephone: this.telephone
        })
        .then(response => {
          this.response = response;
        })
        .catch(error => {
          this.error = error;
        });
        this.$vToastify.info({
              body: "Clinical Centre Administrator "+ this.firstName + " " + this.lastName + " has been added." ,
              title: "Info",
              type: "info",
              canTimeout: true,
              append: false
            });
    }
  }
};
</script>
