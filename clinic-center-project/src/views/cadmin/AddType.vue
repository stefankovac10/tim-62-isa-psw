<template>
  <div class="d-flex p-2 justify-content-center">
    <form accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Add type</h1>

      <label class="p-2">Name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />

      <label class="p-2">Description</label>
      <input type="text" class="p-2" id="description" name="description" v-model="description" />
      <br />
      <button class="btn btn-primary p-2" v-on:click="addType">Submit</button>
    </form>
  </div>
</template>

<script>
// import instance from "@/services/Api.js";
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      name: undefined,
      description: undefined,
      response: undefined,
      error: undefined
    };
  },
  methods: {
    addType: function() {
      httpClient
        .post("/types", {
          name: this.name,
          description: this.description
        })
        .then(response => {
          response;
        })
        .catch(error => {
          this.error = error;
        });

      this.$router.push("/cadmin/types");
    }
  }
};
</script>
