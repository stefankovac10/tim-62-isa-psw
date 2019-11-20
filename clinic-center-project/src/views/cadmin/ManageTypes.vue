<template>
  <div class="d-flex p-2 justify-content-center">
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
            <button type="button" class="btn btn-primary" v-on:click="edit">Edit</button>
            <button type="button" class="btn btn-danger" v-on:click="remove">Delete</button>
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
            <button type="submit" class="btn btn-primary" v-on:click="save">Save</button>
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
      name: undefined,
      description: undefined,
      types: undefined,
      mode: "VIEW"
    };
  },
  created: function() {
    httpClient
      .get("/types/all")
      .then(response => {
        alert("success");
        alert(response.data.error);
        this.types = response.data;
      })
      .error(error => {
        if (error.response && error.response.status === 401) {
          window.location.href = "logon";
        } else {
          alert(error.response);
        }
      });
  },
  methods: {
    edit: function() {
      this.mode = "EDIT";
    },
    save: function() {
      this.mode = "VIEW";
    },
    remove: function() {}
  }
};
</script>