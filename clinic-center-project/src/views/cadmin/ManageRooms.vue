<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column">
      <h1>Manage rooms</h1>
      <label class="p-2">Room type</label>
      <select class="p-2" id="type" name="type" v-model="type">
        <option>Examination</option>
        <option>Operation</option>
      </select>
      <div
        class="card border-primary mb-3 d-flex flex-row flex-wrap"
        style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
      >
        <div class="card-body" v-for="room in rooms" v-bind:key="room.id">
          <h4 class="card-title">Room name: {{room.name}}</h4>
          <p class="card-text">Number: {{room.number}}</p>
          <p class="card-text">Clinic: {{room.clinic}}</p>
          <button type="button" class="btn btn-primary" v-on:click="edit(id)">Edit</button>
          <button type="button" class="btn btn-danger" v-on:click="remove">Delete</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      rooms: undefined,
      type: undefined
    };
  },
  watch: {
    type: function() {
      let path = "/rooms/" + this.type.toLowerCase() + "/all";
      httpClient
        .get(path)
        .then(function(response) {
          this.rooms = response.data;
        })
        .catch(function(error) {
          alert(error.response.data);
        });
    }
  },
  methods: {
    edit: function(id) {
      this.$router.push("/cadmin/editRoom/" + id);
    },
    delete: function() {}
  }
};
</script>