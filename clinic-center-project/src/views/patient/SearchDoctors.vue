<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Search doctors</h1>
      <br />

      <label class="p-2">First name</label>
      <input type="text" class="p-2" id="firstName" name="firstName" v-model="firstName" />

      <label class="p-2">Last name</label>
      <input type="text" class="p-2" id="lastName" name="lastName" v-model="lastName" />

      <label class="p-2">E-mail</label>
      <input type="text" class="p-2" id="email" name="email" v-model="email" />

      <label class="p-2">City</label>
      <input type="text" class="p-2" id="city" name="city" v-model="city" />

      <label class="p-2">Country</label>
      <input type="text" class="p-2" id="country" name="country" v-model="country" />

      <label class="p-2">Clinic</label>
      <input type="text" class="p-2" id="clinic" name="clinic" v-model="clinic" placeholder="Enter name of the clinic"/>

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="search">Search</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      firstName: undefined,
      lastName: undefined,
      email: undefined,
      city: undefined,
      country: undefined,
      clinic: undefined
    };
  },
  methods: {
    search: function() {
      httpClient
        .get("/search", {
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email,
            city: this.city,
            country: this.country,
            clinic: this.clinic
        })
        .then(response => {
          this.response = response;
        })
        .catch(error => {
          this.error = error;
        });
    }
  }
};
</script>
