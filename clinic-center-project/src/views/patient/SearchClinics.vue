<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Search clinics</h1>
      <br />

      <label class="p-2">Name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />

      <label class="p-2">Address</label>
      <input type="text" class="p-2" id="address" name="address" v-model="address" />

      <label class="p-2">Description</label>
      <input type="text" class="p-2" id="description" name="description" v-model="description" />

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="search">Search</button>
    </form>

    <div v-for="clinic in clincis" :key="clinic.id">
        <div class="card-header">{{clinic.name}}</div>
    </div>

  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      name: undefined,
      address: undefined,
      description: undefined,
      clinics: []
    };
  },
  methods: {
    search: function() {
      httpClient
        .post("/clinics/search", {
            name: this.name,
            address: this.address,
            description: this.description
        })
        .then(response => {
          this.response = response;
          this.clinics = response.data;
        })
        .catch(error => {
          this.error = error;
        });
    }
  }
};
</script>
