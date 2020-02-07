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
      error: undefined,
      clinic: undefined
    };
  },
  mounted() {
    httpClient
      .get("users/admin/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.clinic = response.data.clinic;
      })
      .error(() => {
        this.$vToastify.error({
          body: "Error retrieving clinic",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          duration: 2000
        });
      });
  },
  methods: {
    addType: function() {
      httpClient
        .post("/types", {
          name: this.name,
          description: this.description,
          clinic: this.clinic
        })
        .then(() => {
          this.$vToastify.info({
            body: "Type of examination " + this.name + " has been added.",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error while adding type of examination",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });

      this.$router.push("/cadmin/types");
      // location.reload();
    }
  }
};
</script>
