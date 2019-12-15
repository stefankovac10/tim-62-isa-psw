<template>
  <div class="d-flex p-2 justify-content-center" v-bind:key="componentKey">
    <div class="d-flex flex-column p-2 justify-content-center">
      <h1>Manage types</h1>

      <div v-if="mode == 'VIEW'" class="d-flex flex-row flex-wrap p-2 justify-content-center">
        <div
          v-for="type in types"
          v-bind:key="type.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">{{type.name}}</h4>
            <p class="card-text">{{type.description}}</p>
            <button type="button" class="btn btn-primary" v-on:click="edit(type)">Edit</button>
            <button type="button" class="btn btn-danger" v-on:click="remove(type.id)">Delete</button>
          </div>
        </div>
      </div>
      <div v-else class="d-flex flex-row flex-wrap p-2 justify-content-center">
        <form>
          <fieldset>
            <legend>Change type of examination</legend>
            <div class="form-group">
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
              <textarea class="form-control" id="descriptionClinic" v-model="description" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary" v-on:click.prevent="save">Save</button>
          </fieldset>
        </form>
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
      mode: "VIEW",
      componentKey: 0
    };
  },
  mounted: function() {
    this.componentKey += 1;
    httpClient
      .get("/types/all")
      .then(response => {
        this.types = response.data;
      })
      .catch(error => {
        if (error.response.status == 302) this.types = error.response.data;
      });
  },
  methods: {
    edit: function(type) {
      this.mode = "EDIT";
      this.name = type.name;
      this.description = type.description;
      this.id = type.id;
      this.componentKey += 1;
    },
    save: function() {
      this.mode = "VIEW";
      httpClient
        .put("/types", {
          id: this.id,
          name: this.name,
          description: this.description
        })
        .then(response => {
          response;
        })
        .catch(error => {
          alert(error);
        });
      this.componentKey += 1;
      this.$vToastify.info({
          body: "Type of examination has been edited." ,
          title: "Success",
          type: "success",
          canTimeout: true,
          append: false, duration: 2000
        });
    },
    remove: function(id) {
      httpClient
        .delete("/types/" + id)
        .then(response => {
          response;
        })
        .catch(error => {
          alert(error);
        });
      this.componentKey += 1;
      this.types.slice();
      this.$vToastify.info({
        body: "Type of examination has been deleted." ,
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false, duration: 2000
      });
    }
  }
};
</script>