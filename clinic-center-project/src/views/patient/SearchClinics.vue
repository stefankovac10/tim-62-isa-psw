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

      <br />
    </form>

    <table class="table table-hover" id="clinicsTable">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Address</th>
          <th scope="col">Description</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="clinic in clinics" :key="clinic.id" class="table-primary">
          <td>{{clinic.name}}</td>
          <td>{{clinic.address}}</td>
          <td>{{clinic.description}}</td>
        </tr>
      </tbody>
    </table> 
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";

export default {
  data: function() {
    return {
      name: "",
      address: "",
      description: "",
      clinics: []
    };
  },
  mounted() {
    httpClient
      .get("/clinics/all")
      .then(response => {
        this.clinics = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
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
