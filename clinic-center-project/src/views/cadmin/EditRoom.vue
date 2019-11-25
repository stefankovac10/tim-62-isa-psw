<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Add room</h1>
      <br />

      <label class="p-2">Room number</label>
      <input type="text" class="p-2" id="number" name="number" v-model="number" />

      <label class="p-2">Room name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />

      <label class="p-2">Room type</label>
      <select class="p-2" id="type" name="type" v-model="type">
        <option>Examination</option>
        <option>Operation</option>
      </select>
      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="update">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      name: undefined,
      number: undefined,
      room: undefined
    };
  },
  mounted() {
    httpClient
      .get("/rooms/get/" + this.$route.params)
      .then(function(response) {
        this.room = response.data;
        this.name = this.room.name;
        this.number = this.room.number;
      })
      .catch(function(error) {
        alert(error.response.data);
      });
  }
};
</script>