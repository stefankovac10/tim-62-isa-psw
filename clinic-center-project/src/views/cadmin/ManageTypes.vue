<template>
  <div class="d-flex p-2 justify-content-center" :key="componentKey">
    <div class="d-flex flex-column p-2 justify-content-center">
      <h1>Manage types</h1>

      <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
        <div
          v-for="type in types"
          v-bind:key="type.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">{{type.name}}</h4>
            <p class="card-text">{{type.description}}</p>
            <button
              type="button"
              class="btn btn-primary"
              data-toggle="modal"
              data-target="#editModal"
              data-whatever="@mdo"
              v-on:click="edit(type)"
            >Edit</button>
            <button type="button" class="btn btn-danger" v-on:click="remove(type.id)">Delete</button>
          </div>
        </div>
      </div>

      <div id="editModal" class="modal">
        <div class="modal-dialog justify-content-center" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Edit room</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
              <div class="modal-body d-flex flex-column">
                <label for="exampleInputEmail1">Name</label>
                <input
                  type="text"
                  class="form-control"
                  id="nameClinic"
                  v-model="name"
                  placeholder="Enter name"
                />
              </div>
              <div class="form-group">
                <label for="exampleTextarea">Description</label>
                <textarea
                  class="form-control"
                  id="descriptionClinic"
                  v-model="description"
                  rows="3"
                ></textarea>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-primary"
                  data-dismiss="modal"
                  v-on:click.prevent="update"
                >Save changes</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  name: "listDiagnosis",
  data: function() {
    return {
      id: undefined,
      name: undefined,
      description: undefined,
      types: undefined,
      clinic: undefined,
      componentKey: 0
    };
  },
  mounted: function() {
    httpClient
      .get("/users/admin/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.clinic = response.data.clinic;
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Error retrieving clinic",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          duration: 2000
        });
      })
      .then(() => {
        httpClient
          .get("/types/clinic/" + this.clinic.id)
          .then(response => {
            this.types = response.data;
          })
          .catch(error => {
            if (error.response.status == 302) this.types = error.response.data;
          });
      });
  },
  methods: {
    edit: function(type) {
      this.name = type.name;
      this.description = type.description;
      this.id = type.id;
    },
    update: function() {
      httpClient
        .put("/types", {
          id: this.id,
          name: this.name,
          description: this.description,
          clinic: this.clinic
        })
        .then(() => {
          this.$vToastify.info({
            body: "Type of examination has been edited.",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error updating type",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
      location.reload();
      this.componentKey += 1;
    },
    remove: function(id) {
      httpClient
        .delete("/types/" + id)
        .then(() => {
          this.$vToastify.info({
            body: "Type of examination has been deleted.",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error deleting type",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
      this.componentKey += 1;
      location.reload();
    }
  }
};
</script>