<template>
  <div class="d-flex p-2 justify-content-center">
    <form class="d-flex flex-column flex-wrap p-2">
      <h1>Edit clinic</h1>
      <br />
      <label>Name:</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />
      <label>Description:</label>
      <input type="text" class="p-2" id="description" name="description" v-model="description" />
      <label>Address:</label>
      <input type="text" class="p-2" id="address" name="address" v-model="address" />

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="edit">Edit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      clinic: undefined,
      name: undefined,
      description: undefined,
      address: undefined,
      id: this.$route.params.id
    };
  },
  mounted() {
    httpClient
      .get("/clinics/1")
      .then(response => {
        this.clinic = response.data;
        this.name = this.clinic.name;
        this.description = this.clinic.description;
        this.address = this.clinic.address;
      })
      .catch(error => {
        if (error.response != undefined && error.response.status == 302) {
          this.clinic = error.response.data;
          this.name = this.clinic.name;
          this.description = this.clinic.description;
          this.address = this.clinic.address;
        }
      });
  },
  methods: {
    edit: function() {
      httpClient
        .put("/clinics", {
          id: this.clinic.id,
          name: this.name,
          description: this.description,
          address: this.address
        })
        .then(response => {
          this.clinic = response.data;
          this.$router.push("/cadmin/clinic"); // " + this.id);
        })
        .catch(error => {
          alert(error);
        });
    }
  }
};
</script>